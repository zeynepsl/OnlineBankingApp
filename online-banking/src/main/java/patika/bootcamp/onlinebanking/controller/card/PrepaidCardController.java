package patika.bootcamp.onlinebanking.controller.card;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.card.CreatePrepaidCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.PrepaidCardResponseDto;
import patika.bootcamp.onlinebanking.model.card.PrepaidCard;
import patika.bootcamp.onlinebanking.service.facade.PrepaidCardFacade;
import patika.bootcamp.onlinebanking.validator.Validator;

@RestController
@RequestMapping("api/prepaidcards")
@RequiredArgsConstructor
public class PrepaidCardController {
	private final PrepaidCardFacade prepaidCardFacade;
	private final Validator<Long> idValidator;
	
	@PostMapping("/")
	ResponseEntity<PrepaidCardResponseDto> create(@RequestBody CreatePrepaidCardRequestDto createPrepaidCardRequestDto){
		return prepaidCardFacade.create(createPrepaidCardRequestDto);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<PrepaidCardResponseDto> get(@PathVariable Long id){
		idValidator.validate(id);
		return prepaidCardFacade.get(id);
	}
	
	@PutMapping("/")
	ResponseEntity<PrepaidCardResponseDto> update(@RequestBody PrepaidCard prepaidCard){
		return prepaidCardFacade.update(prepaidCard);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id){
		idValidator.validate(id);
		return prepaidCardFacade.delete(id);
	}
	
	@GetMapping("/")
	ResponseEntity<List<PrepaidCardResponseDto>> getAll(){
		return prepaidCardFacade.getAll();
	}
	
	@GetMapping("/customer/{customerId}")
	ResponseEntity<PrepaidCardResponseDto> findByCustomer_Id(@PathVariable Long customerId){
		return prepaidCardFacade.findByCustomer_Id(customerId);
	}
	
	@GetMapping("/balance/{id}")
	ResponseEntity<BigDecimal> getBalance(@PathVariable Long id){
		idValidator.validate(id);
		return prepaidCardFacade.getBalance(id);
	}
}
