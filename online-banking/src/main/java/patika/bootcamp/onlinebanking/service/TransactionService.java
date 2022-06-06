package patika.bootcamp.onlinebanking.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;
import patika.bootcamp.onlinebanking.model.transaction.Transaction;

public interface TransactionService extends BaseService<Transaction> {

	Transaction monenyTransaction(Transaction transaction) throws IOException;

	void validateBalance(BigDecimal accountBalance, BigDecimal amount) throws BaseException;

	void validateAccountType(AccountType accountType) throws BaseException;

	void deductFromAccount(Account from, BigDecimal amount);
	
	boolean currenciesAreNotEqual(String fromCurrency, String toCurrency);
	
	BigDecimal calculateAmountWithToCurrency(BigDecimal transactionAmount, String toCurrency, String fromCurrency)
			throws IOException;
	
	List<Transaction> findByTransactionDate(Date transacionDate);
	List<Transaction> findByModeOfPayment(ModeOfPayment modeOfPayment);
	List<Transaction> findBySenderIbanNo(String ibanNo);
	List<Transaction> findByRecipientIbanNo(String ibanNo);
	List<Transaction> findBySenderAccountId(Long id);
	List<Transaction> findByTransactionDateBetweenAndsenderCustomerNumber(Date startDate, Date endDate, String senderCustomerNumber);


}
