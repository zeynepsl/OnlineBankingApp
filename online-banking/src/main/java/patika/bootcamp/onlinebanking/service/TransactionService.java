package patika.bootcamp.onlinebanking.service;

import java.math.BigDecimal;

import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.model.transaction.Transaction;

public interface TransactionService {

	void monenyTransaction(Transaction transaction);

	void validateBalance(BigDecimal accountBalance, BigDecimal amount) throws BaseException;

	void validateAccountType(AccountType accountType) throws BaseException;

	void deductFromAccount(Account from, BigDecimal amount);
	
	//doviz donusumu
	
	//kullanıcının İki hesabı arası para transferi yapılabilecek
	
	//bunu yaptık: --> validateType()
	//vadesiz mevduat hesabı başka hesaplara para transferi için kullanılabilecekken birikim hesabından doğrudan para transferi yapılamayacak.

	/*
	 * Bir müşteri farklı para birimlerinde açılan hesaplar arası transfer yapmak
	 * isterse güncel para kuru ile dönüşüm yapılıp transfer öyle
	 * gerçekleştirilemeli. Transfer işlemleri sadece IBAN üzerinden
	 * gerçekleştirilebilecek.
	 */

}
