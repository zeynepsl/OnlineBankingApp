package patika.bootcamp.onlinebanking.service.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.PrepaidCardConverter;
import patika.bootcamp.onlinebanking.dto.card.CreatePrepaidCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.PrepaidCardResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.PrepaidCard;
import patika.bootcamp.onlinebanking.service.PrepaidCardService;
import patika.bootcamp.onlinebanking.service.facade.PrepaidCardFacade;

@Service
@RequiredArgsConstructor
public class PrepaidCardFacadeImpl implements PrepaidCardFacade{
	private final PrepaidCardService prepaidCardService;
	private final PrepaidCardConverter prepaidCardConverter;

	@Override
	public ResponseEntity<PrepaidCardResponseDto> create(CreatePrepaidCardRequestDto createPrepaidCardRequestDto)
			throws BaseException {
		PrepaidCard prepaidCard = prepaidCardConverter.toPrepaidCard(createPrepaidCardRequestDto);
		prepaidCard = prepaidCardService.create(prepaidCard);
		return new ResponseEntity<>(prepaidCardConverter.toPrepaidCardResponseDto(prepaidCard), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<PrepaidCardResponseDto> get(Long id) throws BaseException {
		PrepaidCard prepaidCard = prepaidCardService.get(id);
		return ResponseEntity.ok(prepaidCardConverter.toPrepaidCardResponseDto(prepaidCard));
	}

	@Override
	public ResponseEntity<PrepaidCardResponseDto> update(CreatePrepaidCardRequestDto createPrepaidCardRequestDto) {
		PrepaidCard prepaidCard = prepaidCardConverter.toPrepaidCard(createPrepaidCardRequestDto);
		prepaidCard = prepaidCardService.create(prepaidCard);
		return ResponseEntity.ok(prepaidCardConverter.toPrepaidCardResponseDto(prepaidCard));
	}

	@Override
	public ResponseEntity<?> delete(Long id) throws BaseException {
		prepaidCardService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<PrepaidCardResponseDto>> getAll() {
		List<PrepaidCard> prepaidCards = prepaidCardService.getAll();
		return ResponseEntity.ok(toPrepaidCardResponseDtoList(prepaidCards));
	}

	public List<PrepaidCardResponseDto> toPrepaidCardResponseDtoList(List<PrepaidCard> prepaidCards) {
		List<PrepaidCardResponseDto> prepaidCardResponseDtos = new ArrayList<PrepaidCardResponseDto>();
		prepaidCards.forEach(prepaidCard -> {
			PrepaidCardResponseDto prepaidCardResponseDto = prepaidCardConverter.toPrepaidCardResponseDto(prepaidCard);
			prepaidCardResponseDtos.add(prepaidCardResponseDto);
		});
		return prepaidCardResponseDtos;
	}

	@Override
	public ResponseEntity<PrepaidCardResponseDto> findByCustomer_Id(Long customerId) throws BaseException {
		PrepaidCard prepaidCard = prepaidCardService.findByCustomer_Id(customerId);
		return ResponseEntity.ok(prepaidCardConverter.toPrepaidCardResponseDto(prepaidCard));
	}

	@Override
	public ResponseEntity<BigDecimal> getBalance(Long prepaidCardId) {
		PrepaidCard prepaidCard = prepaidCardService.get(prepaidCardId);
		return ResponseEntity.ok(prepaidCard.getBalance());
	}
}
