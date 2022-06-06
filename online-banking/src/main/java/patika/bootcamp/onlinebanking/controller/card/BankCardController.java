package patika.bootcamp.onlinebanking.controller.card;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.card.BankCardResponseDto;
import patika.bootcamp.onlinebanking.dto.card.CreateBankCardRequestDto;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.service.facade.BankCardFacade;
import patika.bootcamp.onlinebanking.validator.Validator;

@RestController
@RequestMapping("api/bankcards")
@RequiredArgsConstructor
public class BankCardController {
	private final BankCardFacade bankCardFacade;
	private final Validator<Long> idValidator;
	
	@PostMapping("/")
	ResponseEntity<BankCardResponseDto> create(@RequestBody CreateBankCardRequestDto createBankCardRequestDto){
		return bankCardFacade.create(createBankCardRequestDto);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<BankCardResponseDto> get(@PathVariable Long id){
		idValidator.validate(id);
		return bankCardFacade.get(id);
	}
	
	@PutMapping("/")
	ResponseEntity<BankCardResponseDto> update(@RequestBody BankCard bankCard){
		return bankCardFacade.update(bankCard);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id){
		idValidator.validate(id);
		return bankCardFacade.delete(id);
	}
	
	@GetMapping("/")
	ResponseEntity<List<BankCardResponseDto>> getAll(){
		return bankCardFacade.getAll();
	}

	@GetMapping("/customer/{customerId}")
	ResponseEntity<BankCardResponseDto> findByCustomerId(@PathVariable Long customerId){
		idValidator.validate(customerId);
		return bankCardFacade.findByCustomerId(customerId);
	}
	
	@GetMapping("/account/{accountId}")
	ResponseEntity<BankCardResponseDto> findByAccountId(@PathVariable Long accountId){
		idValidator.validate(accountId);
		return bankCardFacade.findByAccountId(accountId);
	}
	
	@GetMapping("/balance/{bankCardId}")
	ResponseEntity<BigDecimal> getAccountBalance(@PathVariable Long bankCardId){
		idValidator.validate(bankCardId);
		return bankCardFacade.getAccountBalance(bankCardId);
	}
	
	@PatchMapping("/withdraw/{amount}")
	ResponseEntity<?> withdraw(@RequestBody BankCard bankCard, @RequestParam String password, @PathVariable BigDecimal amount){
		return bankCardFacade.withdraw(bankCard, password, amount);
	}
	
	@PatchMapping("/deposit/{amount}")
	ResponseEntity<?> deposit(@RequestBody BankCard bankCard, @PathVariable String password, @PathVariable BigDecimal amount){
		return bankCardFacade.deposit(bankCard, password, amount);
	}
	
	@GetMapping("/iban")
	ResponseEntity<String> getIban(@RequestBody BankCard bankCard, @RequestParam String password){
		return bankCardFacade.getIban(bankCard, password);
	}
	
	@GetMapping("/card/{cardNumber}")
	ResponseEntity<BankCardResponseDto> findByCardNumber(@PathVariable String cardNumber){
		return bankCardFacade.findByCardNumber(cardNumber);
	}
}
