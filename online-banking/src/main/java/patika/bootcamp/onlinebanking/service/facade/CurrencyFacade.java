package patika.bootcamp.onlinebanking.service.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.account.CreateCurrencyRequestDto;
import patika.bootcamp.onlinebanking.dto.account.CurrencyResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;

public interface CurrencyFacade {
	ResponseEntity<CurrencyResponseDto> create(CreateCurrencyRequestDto createCurrencyRequestDto);
	ResponseEntity<CurrencyResponseDto> update(CreateCurrencyRequestDto createCurrencyRequestDto);
	ResponseEntity<?> delete(Long id);
	ResponseEntity<CurrencyResponseDto> get(Long id);
	
	ResponseEntity<List<CurrencyResponseDto>> getAll();
	ResponseEntity<CurrencyResponseDto> findByName(String name) throws BaseException;
	ResponseEntity<CurrencyResponseDto> findByCode(String code) throws BaseException;
	ResponseEntity<CurrencyResponseDto> findBySymbol(String symbol) throws BaseException;
}
