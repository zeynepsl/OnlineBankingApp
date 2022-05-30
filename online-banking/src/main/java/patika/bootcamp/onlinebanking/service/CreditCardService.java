package patika.bootcamp.onlinebanking.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.CreditCard;

public interface CreditCardService extends BaseService<CreditCard>{
	
	CreditCard findByCustomer_Id(Long customerId);
	CreditCard findByCardNumber(String cardNumber);
	List<CreditCard> findByAccountCutOffDate(Date accountCutOffDate);
	
	
	BigDecimal getCardLimit(Long creditCardId);
	BigDecimal getAvailableLimit(Long creditCardId);
	BigDecimal getPeriodExpenditures(Long creditCardId);
	BigDecimal getAmountOfDebt(Long creditCardId);//borç sorgulam - aekstre gibi olabilir mi?
	void otoPaymentDebt() throws BaseException;//ilk önce tl hesabına 
	
	void moneyTransfer(CreditCard creditCard, String password, String to, BigDecimal amount) throws BaseException;//alışveriş - market
	void onlineMoneyTransfer(String cardNo, String firstName, String lastName, String cvv, Date dueDate, String to, BigDecimal amount);//online alışveriş
	
	void paymentDebtFromCashMachine(CreditCard creditCard, String password);//bankamatik - , BigDecimal amount
	
	void paymentDebtFromAccount(Long accountId);//hesaptan borç öde
}
