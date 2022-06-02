package patika.bootcamp.onlinebanking.service;

import java.math.BigDecimal;
import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.BankCard;

public interface BankCardService extends BaseService<BankCard>{
	BankCard findByCustomerId(Long customerId);
	BankCard findByAccountId(Long accountId);
	BigDecimal getAccountBalance(Long bankCardId);
	
	void withdraw(BankCard bankCard, String password, BigDecimal amount) ;//para çek
	
	void deposit(BankCard bankCard, String password, BigDecimal amount);//para yatır
	String getIban(BankCard bankCard, String password) throws BaseException ;
	BankCard findByCardNumber(String cardNumber);
}
