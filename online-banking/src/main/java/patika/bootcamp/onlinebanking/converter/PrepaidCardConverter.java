package patika.bootcamp.onlinebanking.converter;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.card.CreatePrepaidCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.PrepaidCardResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.card.PrepaidCard;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.service.CustomerService;
import patika.bootcamp.onlinebanking.util.generate.CardNumberGenerator;

@Component
@RequiredArgsConstructor
public class PrepaidCardConverter {
	
	private final CustomerConverter customerConverter;
	
	public PrepaidCard toPrepaidCard(CreatePrepaidCardRequestDto createPrepaidCardRequestDto) {
		PrepaidCard prepaidCard = new PrepaidCard();
		prepaidCard.setPassword(createPrepaidCardRequestDto.getPassword());
		prepaidCard.setCreatedAt(new Date());
		prepaidCard.setCreatedBy("");
		prepaidCard.setUpdatedAt(new Date());
		prepaidCard.setUpdatedBy("");
		return prepaidCard;
	}

	public PrepaidCardResponseDto toPrepaidCardResponseDto(PrepaidCard prepaidCard) {
		PrepaidCardResponseDto prepaidCardResponseDto = new PrepaidCardResponseDto();
		prepaidCardResponseDto.setId(prepaidCard.getId());
		prepaidCardResponseDto.setAccountBalance(prepaidCard.getBalance());
		prepaidCardResponseDto.setCardNumber(prepaidCard.getCardNumber());
		
		CustomerResponseDto customerResponseDto = customerConverter.toCustomerResponseDto(prepaidCard.getCustomer());
		prepaidCardResponseDto.setCustomerResponseDto(customerResponseDto);
		
		prepaidCardResponseDto.setIsActive(prepaidCard.getIsActive());
		prepaidCardResponseDto.setMinBalance(prepaidCard.getMinBalance());
		prepaidCardResponseDto.setMaxBalance(prepaidCard.getMaxBalance());
		
		return prepaidCardResponseDto;
	}

}
