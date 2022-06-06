package patika.bootcamp.onlinebanking;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import patika.bootcamp.onlinebanking.controller.account.CurrencyController;
import patika.bootcamp.onlinebanking.dto.account.CreateCurrencyRequestDto;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.service.CurrencyService;

@SpringBootTest
public class CurrencyTests {
	@Autowired
	CurrencyController currencyController;
	
	@Autowired
	CurrencyService currencyService;
	
	@Test
	void should_create_success_currency() {
		CreateCurrencyRequestDto createCurrencyRequestDto = new CreateCurrencyRequestDto();
		createCurrencyRequestDto.setCode("TRY");
		createCurrencyRequestDto.setName("Turkish lira");
		createCurrencyRequestDto.setSymbol("₺");
		
		currencyController.create(createCurrencyRequestDto);
		
		Currency currencyToCheck = currencyService.findByCode("TRY");
		Assertions.assertThat(currencyToCheck.getId()).isNotNull();	
		Assertions.assertThat(currencyToCheck.getCode()).isEqualTo("TRY");
	}
	
	@Test
	void should_delete_success_currency() {
		CreateCurrencyRequestDto createCurrencyRequestDto = new CreateCurrencyRequestDto();
		createCurrencyRequestDto.setCode("USD");
		createCurrencyRequestDto.setName("United States dollar");
		createCurrencyRequestDto.setSymbol("$");
		
		currencyController.create(createCurrencyRequestDto);
		
		Currency currencyToCheck = currencyService.findByCode("USD");
		Assertions.assertThat(currencyToCheck.getId()).isNotNull();	
		Assertions.assertThat(currencyToCheck.getCode()).isEqualTo("USD");
		
		currencyController.delete(currencyToCheck.getId());
		Assertions.assertThat(currencyToCheck.getId()).isNull();
	}
}
