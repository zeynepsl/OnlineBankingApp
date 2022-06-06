package patika.bootcamp.onlinebanking.service.facade;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.card.CreateCreditCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreateOnlineTransferByCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreditCardResponseDto;
import patika.bootcamp.onlinebanking.dto.transaction.TransactionWithCardResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.CreditCard;

public interface CreditCardFacade {
	ResponseEntity<CreditCardResponseDto> create(CreateCreditCardRequestDto createCreditCardRequestDto) throws BaseException;
	ResponseEntity<CreditCardResponseDto> get(Long id) throws BaseException;
	ResponseEntity<CreditCardResponseDto> update(CreditCard creditCard);
	ResponseEntity<?> delete(Long id) throws BaseException;
	ResponseEntity<List<CreditCardResponseDto>> getAll();
	
	ResponseEntity<CreditCardResponseDto> findByCustomer_Id(Long customerId);
	ResponseEntity<CreditCardResponseDto> findByCardNumber(String cardNumber);
	ResponseEntity<List<CreditCardResponseDto>> findByAccountCutOffDate(Date accountCutOffDate);
	
	ResponseEntity<List<CreditCardResponseDto>> findCardsThatHaveDebt();
	
	
	ResponseEntity<BigDecimal> getCardLimit(Long creditCardId);
	ResponseEntity<BigDecimal> getAvailableLimit(Long creditCardId);
	ResponseEntity<BigDecimal> getPeriodExpenditures(Long creditCardId);
	ResponseEntity<BigDecimal> getAmountOfDebt(Long creditCardId);
	
	ResponseEntity<TransactionWithCardResponseDto> moneyTransfer(CreditCard creditCard, String password, String to, BigDecimal amount) throws BaseException, IOException;
	ResponseEntity<TransactionWithCardResponseDto> onlineMoneyTransfer(CreateOnlineTransferByCardRequestDto onlineTransferByCardRequestDto) throws IOException;
	
	ResponseEntity<?> paymentDebtFromCashMachine(CreditCard creditCard, String password) throws BaseException, IOException;
	
	ResponseEntity<?> paymentDebtFromAccount(Long accountId);
}
