package patika.bootcamp.onlinebanking.converter.card;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.account.AccountConverter;
import patika.bootcamp.onlinebanking.converter.customer.CustomerConverter;
import patika.bootcamp.onlinebanking.dto.card.BankCardResponseDto;
import patika.bootcamp.onlinebanking.dto.card.CreateBankCardRequestDto;
import patika.bootcamp.onlinebanking.model.card.BankCard;

@Component
@RequiredArgsConstructor
public class BankCardConverter {
	
	private final AccountConverter accountConverter;
	private final CustomerConverter customerConverter;

	public BankCard toBankCard(CreateBankCardRequestDto createBankCardRequestDto) {
		BankCard bankCard = new BankCard();	
	
		bankCard.setCreatedAt(new Date());
		bankCard.setCreatedBy("Zeynep Salman");
		bankCard.setUpdatedAt(new Date());
		bankCard.setUpdatedBy("Zeynep Salman");
		
		return bankCard;
	}

	public BankCardResponseDto toBankCardResponseDto(BankCard bankCard) {
		BankCardResponseDto bankCardResponseDto = new BankCardResponseDto();
		bankCardResponseDto.setId(bankCard.getId());
		bankCardResponseDto.setAccountResponseDto(accountConverter.toAccountResponseDto(bankCard.getAccount()));
		bankCardResponseDto.setCardNumber(bankCard.getCardNumber());
		bankCardResponseDto.setCustomerResponseDto(customerConverter.toCustomerResponseDto(bankCard.getCustomer()));
		bankCardResponseDto.setIsActive(bankCard.getIsActive());
		return bankCardResponseDto;
	}

}
