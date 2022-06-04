package patika.bootcamp.onlinebanking.converter.account;

import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.dto.account.CreateCurrencyRequestDto;
import patika.bootcamp.onlinebanking.dto.account.CurrencyResponseDto;
import patika.bootcamp.onlinebanking.model.account.Currency;

@Component
public class CurrencyConverter {

	public Currency toCurrency(CreateCurrencyRequestDto createCurrencyRequestDto) {
		Currency currency = new Currency();
		currency.setName(createCurrencyRequestDto.getName());
		currency.setCode(createCurrencyRequestDto.getCode());
		currency.setSymbol(createCurrencyRequestDto.getSymbol());
		return currency;
	}

	public CurrencyResponseDto toCurrencyResponseDto(Currency currency) {
		CurrencyResponseDto currencyResponseDto = new CurrencyResponseDto();
		currencyResponseDto.setId(currency.getId());
		currencyResponseDto.setCode(currency.getCode());
		currencyResponseDto.setName(currency.getName());
		currencyResponseDto.setSymbol(currency.getSymbol());
		return currencyResponseDto;
	}
	
}
