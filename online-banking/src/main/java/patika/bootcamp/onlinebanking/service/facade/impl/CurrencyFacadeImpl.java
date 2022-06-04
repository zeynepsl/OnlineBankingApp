package patika.bootcamp.onlinebanking.service.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.account.CurrencyConverter;
import patika.bootcamp.onlinebanking.dto.account.CreateCurrencyRequestDto;
import patika.bootcamp.onlinebanking.dto.account.CurrencyResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.CurrencyServiceOperationException;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.service.CurrencyService;
import patika.bootcamp.onlinebanking.service.facade.CurrencyFacade;

@Service
@RequiredArgsConstructor
public class CurrencyFacadeImpl implements CurrencyFacade{
	
	private final CurrencyService currencyService;
	private final CurrencyConverter currencyConverter;

	@Override
	public ResponseEntity<CurrencyResponseDto> create(CreateCurrencyRequestDto createCurrencyRequestDto) {
		boolean isExists = currencyService.existsByName(createCurrencyRequestDto.getName());
		if (isExists) {
			throw new CurrencyServiceOperationException.CurrencyAlreadyExists("currency already exists");
		}
		
		Currency currency = currencyConverter.toCurrency(createCurrencyRequestDto);
		currency = currencyService.create(currency);
		return new ResponseEntity<>(currencyConverter.toCurrencyResponseDto(currency), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CurrencyResponseDto> update(Currency currency) {
		currency = currencyService.update(currency);
		return ResponseEntity.ok(currencyConverter.toCurrencyResponseDto(currency));
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		currencyService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<CurrencyResponseDto> get(Long id) {
		Currency currency = currencyService.get(id);
		return ResponseEntity.ok(currencyConverter.toCurrencyResponseDto(currency));
	}
	
	@Override
	public ResponseEntity<List<CurrencyResponseDto>> getAll() {
		List<CurrencyResponseDto> currencyResponseDtos = toCurrencyResponseDtoList(currencyService.getAll());
		return ResponseEntity.ok(currencyResponseDtos);
	}

	private List<CurrencyResponseDto> toCurrencyResponseDtoList(List<Currency> currencies) {
		List<CurrencyResponseDto> currencyResponseDtos = new ArrayList<CurrencyResponseDto>();
		currencies.forEach(currency -> {
			CurrencyResponseDto currencyResponseDto = currencyConverter.toCurrencyResponseDto(currency);
			currencyResponseDtos.add(currencyResponseDto);
		});
		return currencyResponseDtos;
	}

	@Override
	public ResponseEntity<CurrencyResponseDto> findByName(String name) throws BaseException {
		Currency currency = currencyService.findByName(name);
		return ResponseEntity.ok(currencyConverter.toCurrencyResponseDto(currency));
	}

	@Override
	public ResponseEntity<CurrencyResponseDto> findByCode(String code) throws BaseException {
		Currency currency = currencyService.findByCode(code);
		return ResponseEntity.ok(currencyConverter.toCurrencyResponseDto(currency));
	}

	@Override
	public ResponseEntity<CurrencyResponseDto> findBySymbol(String symbol) throws BaseException {
		Currency currency = currencyService.findBySymbol(symbol);
		return ResponseEntity.ok(currencyConverter.toCurrencyResponseDto(currency));
	}

}
