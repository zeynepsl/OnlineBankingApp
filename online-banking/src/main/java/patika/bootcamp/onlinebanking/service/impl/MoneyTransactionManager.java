package patika.bootcamp.onlinebanking.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
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
public class MoneyTransactionManager implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final AccountService accountService;
	private final CurrencyConverter currencyConverter;


	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void monenyTransaction(Transaction transaction) throws IOException {
		Account from = accountService.findByIban(transaction.getSenderIbanNo());
		Account to = accountService.findByAccountNumber(transaction.getRecipientIbanNo());

		validateAccountType(from.getAccountType());

		BigDecimal fromBalance = from.getAccountBalance();
		BigDecimal toBalance = to.getAccountBalance();
		
		boolean useAllBalance = transaction.getUseAllBalance();
		if (useAllBalance) {
			deductFromAccount(from, fromBalance);
		} else {
			deductFromAccount(from, transaction.getAmount());
		}

		BigDecimal lockedBalance = from.getLockedBalance();
		
		//para birimi donustor
		String fromCurrency = from.getCurrency().getCode();
		String toCurrency = to.getCurrency().getCode();
		
		fromBalance = validateCurrency(fromBalance, fromCurrency, toCurrency);
		validateBalance(fromBalance, lockedBalance);

		from.setAccountBalance(fromBalance.subtract(lockedBalance));
		to.setAccountBalance(toBalance.add(lockedBalance));
		from.setLockedBalance(BigDecimal.ZERO);

		accountService.update(to);
		accountService.update(from);
		
		transaction.setTransactionDate(new Date());
		transaction.setSenderAccount(from);
		create(transaction);
	}

	public BigDecimal validateCurrency(BigDecimal fromBalance, String fromCurrency, String toCurrency) throws IOException {
		if( !(fromCurrency.equals(toCurrency)) ) {
			Double currency = currencyConverter.converter(toCurrency, fromCurrency);
			fromBalance = fromBalance.multiply(BigDecimal.valueOf(currency));
		}
		return fromBalance;
	}

	@Override
	public void validateAccountType(AccountType fromAccountType) {
		if (fromAccountType == AccountType.SAVINGS_ACCOUNT) {
			throw new TransactionServiceOperationException.UnSupportedAccountType(
					"Money transfer cannot be made from savings account.");
		}
	}

	@Override
	public void validateBalance(BigDecimal accountBalance, BigDecimal amount) throws BaseException {
		if (accountBalance.compareTo(amount) < 0) {
			throw new TransactionServiceOperationException.InsufficientBalance(
					"insufficient balance in your bank account");
		}
	}

	// isolation read commited
	@Override
	public void deductFromAccount(Account from, BigDecimal amount) {
		BigDecimal lockedBalance = from.getLockedBalance();
		lockedBalance.add(amount);
		from.setLockedBalance(lockedBalance);
		accountService.update(from);
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



}
