package patika.bootcamp.onlinebanking.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionToAccountRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionToCardRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.TransactionResponseDto;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;
import patika.bootcamp.onlinebanking.service.facade.TransactionFacade;

@RestController
@RequestMapping("api/transactions")
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionFacade transactionFacade;
	
	@PostMapping("/")
	ResponseEntity<?> monenyTransaction(@RequestBody CreateTransactionRequestDto createTransactionRequestDto) throws IOException{
		return transactionFacade.monenyTransaction(createTransactionRequestDto);
	}
	
	@PostMapping("/toAccount")
	ResponseEntity<?> moneyTransactionToAccount(@RequestBody CreateTransactionToAccountRequestDto transactionToAccountRequestDto) throws IOException{
		return transactionFacade.moneyTransactionToAccount(transactionToAccountRequestDto);
	}
	
	@PostMapping("/toCard")
	ResponseEntity<?> moneyTransactionToCard(@RequestBody CreateTransactionToCardRequestDto transactionToCardRequestDto) throws IOException{
		return transactionFacade.moneyTransactionToCard(transactionToCardRequestDto);
	}
	
	
	@GetMapping("/date/{transactionDate}")
	ResponseEntity<List<TransactionResponseDto>> findByTransactionDate(@PathVariable Date transactionDate){
		return transactionFacade.findByTransactionDate(transactionDate);
	}
	
	@GetMapping("/modeOfPayment/{modeOfPayment}")
	ResponseEntity<List<TransactionResponseDto>> findByModeOfPayment(@PathVariable ModeOfPayment modeOfPayment){
		return transactionFacade.findByModeOfPayment(modeOfPayment);
	}
	
	@GetMapping("/sender/{iban}")
	ResponseEntity<List<TransactionResponseDto>> findBySenderIbanNo(@PathVariable String iban){
		return transactionFacade.findBySenderIbanNo(iban);
	}
	
	@GetMapping("/recipient/{iban}")
	ResponseEntity<List<TransactionResponseDto>> findByRecipientIbanNo(@PathVariable String iban){
		return transactionFacade.findByRecipientIbanNo(iban);
	}
	
	@GetMapping("/sender/{accountId}")
	ResponseEntity<List<TransactionResponseDto>> findBySenderAccountId(@PathVariable Long accountId){
		return transactionFacade.findBySenderAccountId(accountId);
	}
}
