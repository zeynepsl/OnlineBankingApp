package patika.bootcamp.onlinebanking.converter;

import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.dto.card.CreatePrepaidCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.PrepaidCardResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.card.PrepaidCard;
import patika.bootcamp.onlinebanking.model.customer.Customer;

@Component
public class PrepaidCardConverter {
	
	public PrepaidCard toPrepaidCard(CreatePrepaidCardRequestDto createPrepaidCardRequestDto) {
		PrepaidCard prepaidCard = new PrepaidCard();
		
		Customer customer = new Customer();
		customer.setId(createPrepaidCardRequestDto.getCustomerId());
		prepaidCard.setCustomer(customer);
		
		prepaidCard.setPassword(createPrepaidCardRequestDto.getPassword());
		
		return prepaidCard;
	}

	public PrepaidCardResponseDto toPrepaidCardResponseDto(PrepaidCard prepaidCard) {
		PrepaidCardResponseDto prepaidCardResponseDto = new PrepaidCardResponseDto();
		prepaidCardResponseDto.setAccountBalance(prepaidCard.getBalance());
		prepaidCardResponseDto.setCardNumber(prepaidCard.getCardNumber());
		
		CustomerResponseDto customerResponseDto = CustomerConverter.toCustomerResponseDto(prepaidCard.getCustomer());
		prepaidCardResponseDto.setCustomerResponseDto(customerResponseDto);
		
		prepaidCardResponseDto.setIsActive(prepaidCard.getIsActive());
		prepaidCardResponseDto.setMinBalance(prepaidCard.getMinBalance());
		prepaidCardResponseDto.setMaxBalance(prepaidCard.getMaxBalance());
		
		return prepaidCardResponseDto;
	}

}
