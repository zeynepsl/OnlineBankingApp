package patika.bootcamp.onlinebanking.service.facade;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionToAccountRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionToCardRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.TransactionResponseDto;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

public interface TransactionFacade {
	ResponseEntity<TransactionResponseDto> monenyTransaction(CreateTransactionRequestDto createTransactionRequestDto) throws IOException;
	ResponseEntity<TransactionResponseDto> moneyTransactionToAccount(CreateTransactionToAccountRequestDto transactionToAccountRequestDto) throws IOException;
	ResponseEntity<TransactionResponseDto> moneyTransactionToCard(CreateTransactionToCardRequestDto transactionToCardRequestDto) throws IOException;
	
	ResponseEntity<List<TransactionResponseDto>> findByTransactionDate(Date transacionDate);
	ResponseEntity<List<TransactionResponseDto>> findByModeOfPayment(ModeOfPayment modeOfPayment);
	ResponseEntity<List<TransactionResponseDto>> findBySenderIbanNo(String ibanNo);
	ResponseEntity<List<TransactionResponseDto>> findByRecipientIbanNo(String ibanNo);
	ResponseEntity<List<TransactionResponseDto>> findBySenderAccountId(Long id);
	

}
