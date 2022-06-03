package patika.bootcamp.onlinebanking.service.facade;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.card.BankCardResponseDto;
import patika.bootcamp.onlinebanking.dto.card.CreateBankCardRequestDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.BankCard;

public interface BankCardFacade {
	
	ResponseEntity<BankCardResponseDto> create(CreateBankCardRequestDto createBankCardRequestDto) throws BaseException;
	ResponseEntity<BankCardResponseDto> get(Long id) throws BaseException;
	ResponseEntity<BankCardResponseDto> update(BankCard bankCard);
	ResponseEntity<?> delete(Long id) throws BaseException;
	ResponseEntity<List<BankCardResponseDto>> getAll();

	ResponseEntity<BankCardResponseDto> findByCustomerId(Long customerId);
	ResponseEntity<BankCardResponseDto> findByAccountId(Long accountId);
	ResponseEntity<BigDecimal> getAccountBalance(Long bankCardId);
	
	ResponseEntity<?> withdraw(BankCard bankCard, String password, BigDecimal amount);
	ResponseEntity<?> deposit(BankCard bankCard, String password, BigDecimal amount);
	ResponseEntity<String> getIban(BankCard bankCard, String password) throws BaseException ;
	ResponseEntity<BankCardResponseDto> findByCardNumber(String cardNumber);
}
