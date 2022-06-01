package patika.bootcamp.onlinebanking.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.dto.card.CreateOnlineTransferByCardRequestDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.CreditCard;

public interface CreditCardService extends BaseService<CreditCard>{
	
	CreditCard findByCustomer_Id(Long customerId);
	CreditCard findByCardNumber(String cardNumber);
	List<CreditCard> findByAccountCutOffDate(Date accountCutOffDate);
	
	List<CreditCard> findCardsThatHaveDebt();
	
	
	BigDecimal getCardLimit(Long creditCardId);
	BigDecimal getAvailableLimit(Long creditCardId);
	BigDecimal getPeriodExpenditures(Long creditCardId);
	BigDecimal getAmountOfDebt(Long creditCardId);//borc sorgulama
	
	void moneyTransfer(CreditCard creditCard, String password, String to, BigDecimal amount) throws BaseException;//alışveriş - market
	void onlineMoneyTransfer(CreateOnlineTransferByCardRequestDto onlineTransferByCardRequestDto);//online alışveriş
	
	void paymentDebtFromCashMachine(CreditCard creditCard, String password) throws BaseException, IOException;//bankamatik - , BigDecimal amount
	
	void paymentDebtFromAccount(Long accountId);//hesaptan borç öde
}
