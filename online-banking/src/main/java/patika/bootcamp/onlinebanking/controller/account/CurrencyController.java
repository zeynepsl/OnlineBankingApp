package patika.bootcamp.onlinebanking.controller.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.account.CreateCurrencyRequestDto;
import patika.bootcamp.onlinebanking.dto.account.CurrencyResponseDto;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.service.facade.CurrencyFacade;
import patika.bootcamp.onlinebanking.validator.Validator;

@RestController
@RequestMapping("api/currencies")
@RequiredArgsConstructor
@Api(value = "Currency Api documentation")
public class CurrencyController {
	private final CurrencyFacade currencyFacade;
	private final Validator<Long> idValidator;
	
	@PostMapping("/")
	public ResponseEntity<CurrencyResponseDto> create(@RequestBody CreateCurrencyRequestDto createCurrencyRequestDto){
		//valid
		return currencyFacade.create(createCurrencyRequestDto);
	}
	
	@PutMapping("/")
	public ResponseEntity<CurrencyResponseDto> update(@RequestBody Currency currency){
		return currencyFacade.update(currency);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		idValidator.validate(id);
		return currencyFacade.delete(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CurrencyResponseDto> get(@PathVariable Long id){
		idValidator.validate(id);
		return currencyFacade.get(id);
	}
	
	@ApiOperation(value = "find currency by name")
	@GetMapping("/name/{name}")
	public ResponseEntity<CurrencyResponseDto> findByName(@PathVariable String name){
		return currencyFacade.findByName(name);
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<CurrencyResponseDto> findByCode(@PathVariable String code){
		return currencyFacade.findByCode(code);
	}
	
	@GetMapping("/symbol/{symbol}")
	public ResponseEntity<CurrencyResponseDto> findBySymbol(@PathVariable String symbol){
		return currencyFacade.findBySymbol(symbol);
	}
	
}
