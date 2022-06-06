package patika.bootcamp.onlinebanking.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.TransactionServiceOperationException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;
import patika.bootcamp.onlinebanking.model.transaction.Transaction;
import patika.bootcamp.onlinebanking.repository.transaction.TransactionRepository;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.TransactionService;
import patika.bootcamp.onlinebanking.util.converter.CurrencyConverter;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MoneyTransactionManager implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final AccountService accountService;
	private final CurrencyConverter currencyConverter;


	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Transaction monenyTransaction(Transaction transaction) throws IOException {
		Account from = accountService.findByIban(transaction.getSenderIbanNo());
		Account to = accountService.findByIban(transaction.getRecipientIbanNo());
		validateAccountType(from.getAccountType());
		BigDecimal fromBalance = from.getAccountBalance();
		BigDecimal toBalance = to.getAccountBalance();
		log.info("gonderen toplam bakiye: {}", fromBalance);
		log.info("alici toplam bakiye: {}", toBalance);
		boolean useAllBalance = transaction.getUseAllBalance();
		if (useAllBalance) {
			deductFromAccount(from, fromBalance);
		} else {
			deductFromAccount(from, transaction.getAmount());
		}
		log.info("4.");

		BigDecimal transactionAmount = from.getLockedBalance();//locked balance artik transfer yapacagimiz miktar oldu
		
		log.info("----------------------------bakiye kontrol yapilacak----------------------------");
		validateBalance(fromBalance, transactionAmount);
		log.info("locked balance: {}", transactionAmount);
		String fromCurrency = from.getCurrency().getCode();
		String toCurrency = to.getCurrency().getCode();
			
		BigDecimal newTransactionAmount = transactionAmount;
		if( currenciesAreNotEqual(fromCurrency, toCurrency) ) {
			log.info("hesap tipleri farkliymis converter a gecilecek......");
			newTransactionAmount = calculateAmountWithToCurrency(transactionAmount, toCurrency, fromCurrency);
		}
		
		from.setAccountBalance(fromBalance.subtract(transactionAmount));
		to.setAccountBalance(toBalance.add(newTransactionAmount));
		from.setLockedBalance(BigDecimal.ZERO);

		accountService.update(to);
		accountService.update(from);
		
		transaction.setTransactionDate(new Date());
		transaction.setSenderAccount(from);
		transaction.setSenderCustomerNumber(from.getCustomer().getCustomerNumber());
		transaction = create(transaction);
		return transaction;
	}

	@Override
	public BigDecimal calculateAmountWithToCurrency(BigDecimal transactionAmount, String toCurrency, String fromCurrency) throws IOException {
		log.info("farkli cikan para birimleri arasinda donustürme yapilacak");
		log.info("tutar: (donusumden once) {}",transactionAmount);
		Double currency = currencyConverter.converter(toCurrency, fromCurrency);
		transactionAmount = transactionAmount.multiply(BigDecimal.valueOf(currency));
		log.info("tutar: (donusumden sonra) {}",transactionAmount);
		return transactionAmount;
	}

	@Override
	public boolean currenciesAreNotEqual(String fromCurrency, String toCurrency) {
		log.info("para birimleri kontrol");
		if( !(fromCurrency.equals(toCurrency)) ) {
			return true;
		}
		return false;
	}

	@Override
	public void validateAccountType(AccountType fromAccountType) {
		log.info("hesap tipi kontrol...");
		log.info("gonderici hesap tipi: {}", fromAccountType);
		if (fromAccountType == AccountType.SAVINGS_ACCOUNT) {
			throw new TransactionServiceOperationException.UnSupportedAccountType(
					"Money transfer cannot be made from savings account.");
		}
	} 

	@Override
	public void validateBalance(BigDecimal accountBalance, BigDecimal amount) throws BaseException {
		log.info("bakiye kontrol...");
		log.info("bakiye: {}", accountBalance);
		log.info("tutar {}", amount);
		
		//accountBalance.compareTo(amount) != 0 ??
		if (accountBalance.compareTo(amount) < 0) {
			throw new TransactionServiceOperationException.InsufficientBalance(
					"insufficient balance in your bank account");
		}
	}

	// isolation read commited
	@Override
	public void deductFromAccount(Account from, BigDecimal amount) {
		log.info("daha transfer yapilmadi, lockedBalance i guncelleyecegiz");
		BigDecimal lockedBalance = from.getLockedBalance();
		log.info("guncellenmeden once lockedbalance: {}", lockedBalance);
		lockedBalance = lockedBalance.add(amount);
		from.setLockedBalance(lockedBalance);
		accountService.update(from);
		log.info("guncellenmeden sonra lockedbalance: {}", lockedBalance);
	}
	
	@Override
	public Transaction create(Transaction transaction) throws BaseException {
		transaction = transactionRepository.save(transaction);
		return transaction;
	}

	@Override
	public Transaction get(Long id) throws BaseException {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new TransactionServiceOperationException.TransactionNotFound("not found"));
		return transaction;
	}

	@Override
	public Transaction update(Transaction transaction) {
		transaction = transactionRepository.save(transaction);
		return transaction;
	}

	@Override
	public void delete(Long id) throws BaseException {
		Transaction transaction = get(id);
		transactionRepository.delete(transaction);
	}

	@Override
	public List<Transaction> getAll() {
		return transactionRepository.findAll();
	}

	@Override
	public List<Transaction> findBySenderAccountId(Long id) {
		return transactionRepository.findBySenderAccount_Id(id);
	}

	@Override
	public List<Transaction> findByTransactionDate(Date transacionDate) {
		return transactionRepository.findByTransactionDate(transacionDate);
	}

	@Override
	public List<Transaction> findByModeOfPayment(ModeOfPayment modeOfPayment) {
		return transactionRepository.findByModeOfPayment(modeOfPayment);
	}

	@Override
	public List<Transaction> findBySenderIbanNo(String ibanNo) {
		return transactionRepository.findBySenderIbanNo(ibanNo);
	}

	@Override
	public List<Transaction> findByRecipientIbanNo(String ibanNo) {
		return transactionRepository.findByRecipientIbanNo(ibanNo);
	}

	@Override
	public List<Transaction> findByTransactionDateBetweenAndsenderCustomerNumber(Date startDate, Date endDate,
			String senderCustomerNumber) {
		return transactionRepository.findByTransactionDateBetweenAndSenderCustomerNumber(startDate, endDate, senderCustomerNumber);
	}
	
}
