package patika.bootcamp.onlinebanking.service.facade;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.card.CreatePrepaidCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.PrepaidCardResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.PrepaidCard;

public interface PrepaidCardFacade {

	ResponseEntity<PrepaidCardResponseDto> create(CreatePrepaidCardRequestDto createPrepaidCardRequestDto) throws BaseException;
	ResponseEntity<PrepaidCardResponseDto> get(Long id) throws BaseException;
	ResponseEntity<PrepaidCardResponseDto> update(PrepaidCard prepaidCard);
	ResponseEntity<?> delete(Long id) throws BaseException;
	ResponseEntity<List<PrepaidCardResponseDto>> getAll();
	
	ResponseEntity<PrepaidCardResponseDto> findByCustomer_Id(Long customerId) throws BaseException;
	ResponseEntity<BigDecimal> getBalance(Long prepaidCardId);
	
}
