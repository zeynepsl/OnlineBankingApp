package patika.bootcamp.onlinebanking.converter.card;

import java.util.Date;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.bank.BranchConverter;
import patika.bootcamp.onlinebanking.converter.customer.CustomerConverter;
import patika.bootcamp.onlinebanking.dto.bank.BranchResponseDto;
import patika.bootcamp.onlinebanking.dto.card.CreateCreditCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreditCardResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.card.CreditCard;

@Component
@RequiredArgsConstructor
public class CreditCardConverter {
	
	private final BranchConverter branchConverter;
	private final CustomerConverter customerConverter;

	public CreditCard toCreditCard(CreateCreditCardRequestDto createCreditCardRequestDto) {
		CreditCard creditCard = new CreditCard();
		creditCard.setCardLimit(createCreditCardRequestDto.getCardLimit());
		creditCard.setCreatedAt(new Date());
		creditCard.setCreatedBy("Zeynep Salman");
		creditCard.setUpdatedAt(new Date());
		creditCard.setUpdatedBy("Zeynep Salman");
		
		return creditCard;
		
	}

	public CreditCardResponseDto toCreditCardResponseDto(CreditCard creditCard) {
		CreditCardResponseDto creditCardResponseDto = new CreditCardResponseDto();
		creditCardResponseDto.setId(creditCard.getId());
		creditCardResponseDto.setAccountCutOffDate(creditCard.getAccountCutOffDate());
		creditCardResponseDto.setAmountOfDebt(creditCard.getAmountOfDebt());
		creditCardResponseDto.setAvailableLimit(creditCard.getAvailableLimit());
		
		BranchResponseDto branchResponseDto = branchConverter.toBankBranchResponseDto(creditCard.getBankBranch());
		creditCardResponseDto.setBankBranchResponseDto(branchResponseDto);
		
		creditCardResponseDto.setCardLimit(creditCard.getCardLimit());
		creditCardResponseDto.setCardNumber(creditCard.getCardNumber());
		
		CustomerResponseDto customerResponseDto = customerConverter.toCustomerResponseDto(creditCard.getCustomer());
		creditCardResponseDto.setCustomerResponseDto(customerResponseDto);
		
		creditCardResponseDto.setCvv(creditCard.getCvv());
		creditCardResponseDto.setDueDate(creditCard.getDueDate());
		creditCardResponseDto.setIsActive(creditCard.getIsActive());
		creditCardResponseDto.setPaymentDueDate(creditCard.getPaymentDueDate());
		creditCardResponseDto.setPeriodExpenditures(creditCard.getPeriodExpenditures());
		
		return creditCardResponseDto;
	}

}
