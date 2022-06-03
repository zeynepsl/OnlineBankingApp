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
	
	List<Transaction> findByTransactionDate(Date transacionDate);
	List<Transaction> findByModeOfPayment(ModeOfPayment modeOfPayment);
	List<Transaction> findBySenderIbanNo(String ibanNo);
	List<Transaction> findByRecipientIbanNo(String ibanNo);
	List<Transaction> findBySenderAccountId(Long id);

	BigDecimal calculateAmountWithToCurrency(BigDecimal transactionAmount, String toCurrency, String fromCurrency)
			throws IOException;

	boolean currenciesAreNotEqual(String fromCurrency, String toCurrency);
	
	// doviz donusumu

	// kullanıcının İki hesabı arası para transferi yapılabilecek

	// bunu yaptık: --> validateType()
	// vadesiz mevduat hesabı başka hesaplara para transferi için
	// kullanılabilecekken birikim hesabından doğrudan para transferi yapılamayacak.

	/*
	 * Bir müşteri farklı para birimlerinde açılan hesaplar arası transfer yapmak
	 * isterse güncel para kuru ile dönüşüm yapılıp transfer öyle
	 * gerçekleştirilemeli. Transfer işlemleri sadece IBAN üzerinden
	 * gerçekleştirilebilecek.
	 */

}
