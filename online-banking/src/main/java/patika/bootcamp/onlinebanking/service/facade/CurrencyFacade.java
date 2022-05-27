package patika.bootcamp.onlinebanking.service.facade;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.request.CreateCurrencyRequestDto;
import patika.bootcamp.onlinebanking.dto.response.CurrencyResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Currency;

public interface CurrencyFacade {
	ResponseEntity<CurrencyResponseDto> create(CreateCurrencyRequestDto createCurrencyRequestDto);
	ResponseEntity<CurrencyResponseDto> update(CreateCurrencyRequestDto createCurrencyRequestDto);
	ResponseEntity<?> delete(Long id);
	ResponseEntity<CurrencyResponseDto> get(Long id);
	
	ResponseEntity<CurrencyResponseDto> findByName(String name) throws BaseException;
	ResponseEntity<CurrencyResponseDto> findByCode(String code) throws BaseException;
	ResponseEntity<CurrencyResponseDto> findBySymbol(String symbol) throws BaseException;
}
