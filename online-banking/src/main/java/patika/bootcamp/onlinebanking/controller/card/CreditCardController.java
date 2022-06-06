package patika.bootcamp.onlinebanking.controller.card;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.card.CreateCreditCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreateOnlineTransferByCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreditCardResponseDto;
import patika.bootcamp.onlinebanking.dto.transaction.TransactionWithCardResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.service.facade.CreditCardFacade;
import patika.bootcamp.onlinebanking.validator.Validator;

@RestController
@RequestMapping("api/creditcards")
@RequiredArgsConstructor
public class CreditCardController {
	private final CreditCardFacade creditCardFacade;
	private final Validator<Long> idValidator;
	
	@PostMapping("/")
	public ResponseEntity<CreditCardResponseDto> create(@RequestBody CreateCreditCardRequestDto createCreditCardRequestDto){
		return creditCardFacade.create(createCreditCardRequestDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CreditCardResponseDto> get(@PathVariable Long id){
		idValidator.validate(id);
		return creditCardFacade.get(id);
	}
	
	@PutMapping("/")
	public ResponseEntity<CreditCardResponseDto> update(@RequestBody CreditCard creditCard){
		return creditCardFacade.update(creditCard);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		idValidator.validate(id);
		return creditCardFacade.delete(id);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CreditCardResponseDto>> getAll(){
		return creditCardFacade.getAll();
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CreditCardResponseDto> findByCustomer_Id(@PathVariable Long customerId){
		idValidator.validate(customerId);
		return creditCardFacade.findByCustomer_Id(customerId);
	}
	
	@GetMapping("/cardNumber/{cardNumber}")
	public ResponseEntity<CreditCardResponseDto> findByCardNumber(@PathVariable String cardNumber){
		return creditCardFacade.findByCardNumber(cardNumber);
	}
	
	@GetMapping("/cardsWithDebt")
	public ResponseEntity<List<CreditCardResponseDto>> findCardsThatHaveDebt(){
		return creditCardFacade.findCardsThatHaveDebt();
	}
	
	@GetMapping("/cardLimit/{creditCardId}")
	public ResponseEntity<BigDecimal> getCardLimit(@PathVariable Long creditCardId){
		idValidator.validate(creditCardId);
		return creditCardFacade.getCardLimit(creditCardId);
	}
	
	@GetMapping("/availableLimit/{creditCardId}")
	public ResponseEntity<BigDecimal> getAvailableLimit(@PathVariable Long creditCardId){
		idValidator.validate(creditCardId);
		return creditCardFacade.getAvailableLimit(creditCardId);
	}
	
	@GetMapping("/periodExpenditures/{creditCardId}")
	public ResponseEntity<BigDecimal> getPeriodExpenditures(@PathVariable Long creditCardId){
		idValidator.validate(creditCardId);
		return creditCardFacade.getPeriodExpenditures(creditCardId);
	}
	
	@GetMapping("/amountOfDebt/{creditCardId}")
	public ResponseEntity<BigDecimal> getAmountOfDebt(@PathVariable Long creditCardId){
		idValidator.validate(creditCardId);
		return creditCardFacade.getAmountOfDebt(creditCardId);
	}
	
	@PutMapping("/transfer/{to}/{amount}")
	public ResponseEntity<TransactionWithCardResponseDto> moneyTransfer(@RequestBody CreditCard creditCard, @RequestParam String password, @PathVariable String to, @PathVariable BigDecimal amount) throws BaseException, IOException{
		return creditCardFacade.moneyTransfer(creditCard, password, to, amount);
	}
	
	@PatchMapping("/onlineTransfer")
	public ResponseEntity<TransactionWithCardResponseDto> onlineMoneyTransfer(@RequestBody CreateOnlineTransferByCardRequestDto onlineTransferByCardRequestDto) throws IOException{
		return creditCardFacade.onlineMoneyTransfer(onlineTransferByCardRequestDto);
	}
	
	@PutMapping("/paymentDebtWithCashMachine")
	public ResponseEntity<?> paymentDebtFromCashMachine(@RequestBody CreditCard creditCard, @RequestParam String password) throws BaseException, IOException{
		return creditCardFacade.paymentDebtFromCashMachine(creditCard, password);
	}
	
	@PutMapping("/paymentDebtWithAccoun/{accountId}")
	public ResponseEntity<?> paymentDebtFromAccount(@PathVariable Long accountId){
		idValidator.validate(accountId);
		return creditCardFacade.paymentDebtFromAccount(accountId);
	}
}
