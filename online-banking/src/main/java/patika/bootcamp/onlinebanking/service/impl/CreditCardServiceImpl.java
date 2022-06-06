package patika.bootcamp.onlinebanking.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.card.CreateOnlineTransferByCardRequestDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.CreditCardServiceOperationException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;
import patika.bootcamp.onlinebanking.model.transaction.Transaction;
import patika.bootcamp.onlinebanking.repository.card.CreditCardRepository;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.CreditCardService;
import patika.bootcamp.onlinebanking.service.TransactionService;
import patika.bootcamp.onlinebanking.validator.PasswordValidator;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditCardServiceImpl implements CreditCardService {
	private final CreditCardRepository creditCardRepository;
	private final AccountService accountService;
	private final TransactionService transactionService;
	private final PasswordValidator passwordValidator;

	@Override
	public CreditCard create(CreditCard creditCard) throws BaseException {
		creditCard = creditCardRepository.save(creditCard);
		return creditCard;
	}

	@Override
	public CreditCard get(Long id) throws BaseException {
		CreditCard creditCard = creditCardRepository.findById(id)
				.orElseThrow(() -> new CreditCardServiceOperationException.CreditCardNotFound("Credit card not found"));
		return creditCard;
	}

	@Override
	public CreditCard update(CreditCard creditCard) {
		creditCard = creditCardRepository.save(creditCard);
		return creditCard;
	}

	@Override
	public void delete(Long id) throws BaseException {
		CreditCard creditCard = get(id);
		if (creditCard.getAmountOfDebt().compareTo(BigDecimal.ZERO) > 0) {
			throw new CreditCardServiceOperationException.CreditCardCannotDeleted("a card with debt cannot be deleted");
		}
		creditCard = creditCard.removeBankBranch(creditCard.getBankBranch());
		creditCard = creditCard.removeCustomer(creditCard.getCustomer());
		creditCardRepository.delete(creditCard);
	}

	@Override
	public List<CreditCard> getAll() {
		return creditCardRepository.findAll();
	}

	@Override
	public CreditCard findByCustomer_Id(Long customerId) {
		CreditCard creditCard = creditCardRepository.findByCustomer_Id(customerId)
				.orElseThrow(() -> new CreditCardServiceOperationException.CreditCardNotFound("Credit card not found"));
		return creditCard;
	}

	@Override
	public List<CreditCard> findByAccountCutOffDate(Date accountCutOffDate) {
		return creditCardRepository.findByAccountCutOffDate(accountCutOffDate);
	}

	@Override
	public BigDecimal getCardLimit(Long creditCardId) {
		CreditCard creditCard = get(creditCardId);
		return creditCard.getCardLimit();
	}

	@Override
	public BigDecimal getAvailableLimit(Long creditCardId) {
		CreditCard creditCard = get(creditCardId);
		return creditCard.getAvailableLimit();
	}

	@Override
	public BigDecimal getPeriodExpenditures(Long creditCardId) {
		CreditCard creditCard = get(creditCardId);
		return creditCard.getPeriodExpenditures();
	}

	@Override
	public BigDecimal getAmountOfDebt(Long creditCardId) {
		CreditCard creditCard = get(creditCardId);
		return creditCard.getAmountOfDebt();
	}

	@Override
	public Transaction moneyTransfer(CreditCard creditCard, String inputPassword, String to, BigDecimal amount)
			throws BaseException, IOException {
		passwordValidator.validate(inputPassword, creditCard.getPassword());// password ler uyuşmalı uyuşmuyorsa hata
		updateAvailableLimitAndDebtInCreditCard(creditCard, amount);// amount u periyodikHarcamalar ile topla, çıkan
																	// sonuç limiti aşıyorsa hata ver
		updateToAccount(to, amount);// miktari alici hesabina aktar

		String fromCustomerNumber = creditCard.getCustomer().getCustomerNumber();
		Transaction transaction = createTransaction(fromCustomerNumber, to, amount);
		return transaction;
	}


	public Transaction createTransaction(String fromCustomerNumber, String to, BigDecimal amount) {
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setModeOfPayment(ModeOfPayment.KrediKartiIleOdeme);
		transaction.setSenderCustomerNumber(fromCustomerNumber);
		transaction.setTransactionDate(new Date());

		Account toAccount = accountService.findByAccountNumber(to);
		transaction.setRecipientIbanNo(toAccount.getIban());
		// burada senderIbanNo'yu, krediKart herhangi bir hesaba bagli olmadigi icin bos
		// biraktik
		// ay sonu otomatik borc odemede hangi hesap kullanilacaksa, bu transaction
		// satirindaki senderIban, o Account un ibani ile gıncellenecek
		transaction = transactionService.create(transaction);
		return transaction;
	}

	public void updateAvailableLimitAndDebtInCreditCard(CreditCard creditCard, BigDecimal amount) {

		BigDecimal limit = creditCard.getCardLimit();
		BigDecimal newPeriodExpenditures = (creditCard.getPeriodExpenditures()).add(amount);

		validateCreditCardLimit(limit, newPeriodExpenditures);

		creditCard.setAvailableLimit(limit.subtract(newPeriodExpenditures));
		creditCard.setAmountOfDebt(creditCard.getAmountOfDebt().add(amount));
		creditCard.setPeriodExpenditures(newPeriodExpenditures);
		update(creditCard);
	}

	public void validateCreditCardLimit(BigDecimal limit, BigDecimal newPeriodExpenditures) throws BaseException {
		if (newPeriodExpenditures.compareTo(limit) > 0) {
			throw new CreditCardServiceOperationException.InsufficientCreditCardLimit(
					"you are exceeding the credit card limit");
		}
	}

	public void updateToAccount(String to, BigDecimal amount) throws IOException {
		Account toAccount = accountService.findByAccountNumber(to);

		String toCurrency = toAccount.getCurrency().getCode();

		if (transactionService.currenciesAreNotEqual("TRY", toCurrency)) {
			amount = transactionService.calculateAmountWithToCurrency(amount, toCurrency, "TRY");
		}
		toAccount.setAccountBalance(toAccount.getAccountBalance().add(amount));
		accountService.update(toAccount);
	}

	@Override
	public Transaction onlineMoneyTransfer(CreateOnlineTransferByCardRequestDto onlineTransferByCardRequestDto)
			throws IOException {

		String cardNo = onlineTransferByCardRequestDto.getCardNo();
		String firstName = onlineTransferByCardRequestDto.getFirstName();
		String lastName = onlineTransferByCardRequestDto.getLastName();
		BigDecimal amount = onlineTransferByCardRequestDto.getAmount();
		String cvv = onlineTransferByCardRequestDto.getCvv();
		Date dueDate = onlineTransferByCardRequestDto.getDueDate();
		String to = onlineTransferByCardRequestDto.getToAccountNumber();

		CreditCard creditCard = findByCardNumber(cardNo);

		validateOnlineTransferInfo(firstName, lastName, cvv, dueDate, creditCard);
		updateAvailableLimitAndDebtInCreditCard(creditCard, amount);
		updateToAccount(to, amount);

		String fromCustomerNumber = creditCard.getCustomer().getCustomerNumber();
		Transaction transaction = createTransaction(fromCustomerNumber, to, amount);
		return transaction;
	}

	public void validateOnlineTransferInfo(String firstName, String lastName, String cvv, Date dueDate,
			CreditCard creditCard) {
		if (!(creditCard.getCustomer().getFirstName().equals(firstName)
				|| !(creditCard.getCustomer().getLastName().equals(lastName)) || !(creditCard.getCvv().equals(cvv))
				|| (creditCard.getDueDate().compareTo(dueDate) != 0))) {
			throw new CreditCardServiceOperationException.WrongCardInformation("wrong card information");
		}
	}

	@Override
	public void payDebtGivenFromAccount(CreditCard creditCard, BigDecimal amountOfPayment)
			throws BaseException, IOException {
		BigDecimal amountOfDebt = creditCard.getAmountOfDebt();

		if (amountOfDebt.compareTo(amountOfPayment) < 0) {
			throw new CreditCardServiceOperationException.AmountMoreThanDebt(
					"The amount you will pay is more than the credit card debt");
		}

		basePaymentDebt(creditCard, amountOfDebt);
		update(creditCard);
	}

	@Override
	public void payDebtFromCashMachine(CreditCard creditCard, String password) throws BaseException, IOException {
		passwordValidator.validate(password, creditCard.getPassword());
		basePaymentDebt(creditCard, creditCard.getAmountOfDebt());
	}

	@Override
	public void basePaymentDebt(CreditCard creditCard, BigDecimal amountOfDebt) throws BaseException, IOException {
		Branch creditCardBankBranch = creditCard.getBankBranch();

		// kredi kartı sahibinin kredi kart aldığı şubedeki hesaplari:
		List<Account> accounts = accountService.findByBranchCodeAndCustomerId(creditCardBankBranch.getBranchCode(),
				creditCard.getCustomer().getId());

		String customerNumber = creditCard.getCustomer().getCustomerNumber();
		// o şubedeki hesapların arasından ilk once; vadesiz tl hesabına bakılır:
		// vadesiz tl hesabı bakiyesi yeterliyse borç bu hesaptan ödenir:
		accounts.forEach(account -> {
			if ((account.getAccountType() == AccountType.CHECKING_ACCOUNT)
					&& (account.getCurrency().getName() == "TRY")) {
				if ((account.getAccountBalance()).compareTo(amountOfDebt) > 0) {
					discountMoneyFromAccountAndUpdateDebt(creditCard, amountOfDebt, account);
					updateTransaction(account, customerNumber);
					return;
				}
			}
		});

		// vadesiz tl hesabında yeterli bakiye kalmamış, o zaman diğer para
		// birimlerindeki hesaplara bak (forEach continue kabul etmedi :) )
		for (Account account : accounts) {
			if ((account.getAccountType() == AccountType.CHECKING_ACCOUNT)
					&& (account.getCurrency().getName() == "TRY")) {
				continue;
			}
			/*
			 * kredi kartı borcum tl cinsinden o account un currencyTipini al
			 * currencyconverter a gonder( accountCurrencyType dan --> TRY tipine
			 */
			String fromCurrency = account.getCurrency().getCode();
			String toCurrency = "TRY";

			BigDecimal newAmountOfDebt = transactionService.calculateAmountWithToCurrency(amountOfDebt, toCurrency, fromCurrency);
			discountMoneyFromAccountAndUpdateDebt(creditCard, newAmountOfDebt, account);
			updateTransaction(account, customerNumber);
			return;
		}
		throw new CreditCardServiceOperationException.InsufficientBalance(
				"Your credit card debt is not paid. you have insufficient balance");
	}

	public void updateTransaction(Account senderAccount, String senderCustomerNumber) {
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int endMonth = cal.get(Calendar.MONTH) + 1;
		int startMonth = endMonth - 1;
		int year = cal.get(Calendar.YEAR);
		List<Transaction> transactions = transactionService.findByTransactionDateBetweenAndsenderCustomerNumber(
				new Date(year, startMonth, 8), new Date(year, endMonth, 8), senderCustomerNumber);
		for (Transaction transaction : transactions) {
			if( transaction.getSenderAccount() == null && transaction.getSenderIbanNo() == null) {
				transaction.setSenderAccount(senderAccount);
				transaction.setSenderIbanNo(senderAccount.getIban());
				transactionService.update(transaction);
			}
		}
	}

	public void discountMoneyFromAccountAndUpdateDebt(CreditCard creditCard, BigDecimal amountOfDebt, Account account) {
		account.setAccountBalance(account.getAccountBalance().subtract(amountOfDebt));
		accountService.update(account);
		creditCard.setAmountOfDebt(BigDecimal.ZERO);
		update(creditCard);
	}

	@Override
	public void payDebtFromAccount(Long accountId) {
		Account account = accountService.get(accountId);// hesabı getir
		BigDecimal accountBalance = account.getAccountBalance();// 0

		CreditCard creditCard = account.getCustomer().getCreditCard();
		BigDecimal amountOfDebt = creditCard.getAmountOfDebt();// 152

		validateBalance(accountBalance, amountOfDebt);
		discountMoneyFromAccountAndUpdateDebt(creditCard, amountOfDebt, account);
	}

	public void validateBalance(BigDecimal accountBalance, BigDecimal amountOfDebt) {
		if (accountBalance.compareTo(amountOfDebt) < 0) {
			throw new CreditCardServiceOperationException.InsufficientBalance("insufficient balance");
		}
	}

	@Override
	public CreditCard findByCardNumber(String cardNumber) {
		CreditCard creditCard = creditCardRepository.findByCardNumber(cardNumber)
				.orElseThrow(() -> new CreditCardServiceOperationException.CreditCardNotFound("credit card not found"));
		return creditCard;
	}

	@Override
	public List<CreditCard> findCardsThatHaveDebt() {
		List<CreditCard> allCreditCards = new ArrayList<CreditCard>();
		allCreditCards.addAll(getAll());

		List<CreditCard> cardsThatHaveDebt = allCreditCards.stream()
				.filter(c -> c.getAmountOfDebt().compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());

		return cardsThatHaveDebt;
	}

}
