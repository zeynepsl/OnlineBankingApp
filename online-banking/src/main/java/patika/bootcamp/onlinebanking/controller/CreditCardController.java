package patika.bootcamp.onlinebanking.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.card.CreateCreditCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreateOnlineTransferByCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreditCardResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.service.facade.CreditCardFacade;

@RestController
@RequestMapping("api/creditcards")
@RequiredArgsConstructor
public class CreditCardController {
	private final CreditCardFacade creditCardFacade;
	
	@PostMapping("/")
	ResponseEntity<CreditCardResponseDto> create(@RequestBody CreateCreditCardRequestDto createCreditCardRequestDto){
		return creditCardFacade.create(createCreditCardRequestDto);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<CreditCardResponseDto> get(@PathVariable Long id){
		return creditCardFacade.get(id);
	}
	
	@PutMapping("/")
	ResponseEntity<CreditCardResponseDto> update(@RequestBody CreditCard creditCard){
		return creditCardFacade.update(creditCard);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id){
		return creditCardFacade.delete(id);
	}
	
	@GetMapping("/")
	ResponseEntity<List<CreditCardResponseDto>> getAll(){
		return creditCardFacade.getAll();
	}
	
	@GetMapping("/customer/{customerId}")
	ResponseEntity<CreditCardResponseDto> findByCustomer_Id(@PathVariable Long customerId){
		return creditCardFacade.findByCustomer_Id(customerId);
	}
	
	@GetMapping("/cardNumber/{cardNumber}")
	ResponseEntity<CreditCardResponseDto> findByCardNumber(@PathVariable String cardNumber){
		return creditCardFacade.findByCardNumber(cardNumber);
	}
	
	@GetMapping("/cardsWithDebt")
	ResponseEntity<List<CreditCardResponseDto>> findCardsThatHaveDebt(){
		return creditCardFacade.findCardsThatHaveDebt();
	}
	
	@GetMapping("/cardLimit/{creditCardId}")
	ResponseEntity<BigDecimal> getCardLimit(@PathVariable Long creditCardId){
		return creditCardFacade.getCardLimit(creditCardId);
	}
	
	@GetMapping("/availableLimit/{creditCardId}")
	ResponseEntity<BigDecimal> getAvailableLimit(@PathVariable Long creditCardId){
		return creditCardFacade.getAvailableLimit(creditCardId);
	}
	
	@GetMapping("/periodExpenditures/{creditCardId}")
	ResponseEntity<BigDecimal> getPeriodExpenditures(@PathVariable Long creditCardId){
		return creditCardFacade.getPeriodExpenditures(creditCardId);
	}
	
	@GetMapping("/amountOfDebt/{creditCardId}")
	ResponseEntity<BigDecimal> getAmountOfDebt(@PathVariable Long creditCardId){
		return creditCardFacade.getAmountOfDebt(creditCardId);
	}
	
	@PutMapping("/transfer/{senderCustomerId}/{to}/{amount}")
	ResponseEntity<?> moneyTransfer(@PathVariable Long senderCustomerId, @RequestParam String password, @PathVariable String to, @PathVariable BigDecimal amount) throws BaseException, IOException{
		return creditCardFacade.moneyTransfer(senderCustomerId, password, to, amount);
	}
	
	@PatchMapping("/onlineTransfer")
	ResponseEntity<?> onlineMoneyTransfer(@RequestBody CreateOnlineTransferByCardRequestDto onlineTransferByCardRequestDto) throws IOException{
		return creditCardFacade.onlineMoneyTransfer(onlineTransferByCardRequestDto);
	}
	
	@PutMapping("/paymentDebtWithCashMachine")
	ResponseEntity<?> paymentDebtFromCashMachine(@RequestBody CreditCard creditCard, @RequestParam String password) throws BaseException, IOException{
		return creditCardFacade.paymentDebtFromCashMachine(creditCard, password);
	}
	
	@PutMapping("/paymentDebtWithAccoun/{accountId}")
	ResponseEntity<?> paymentDebtFromAccount(@PathVariable Long accountId){
		return creditCardFacade.paymentDebtFromAccount(accountId);
	}
}
