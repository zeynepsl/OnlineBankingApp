package patika.bootcamp.onlinebanking.dto.account;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.card.CreditCardResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.account.CurrencyUnit;

@Getter
@Setter
public class PrimaryAccounResponseDto {
	
	private String accountNumber;
	private BigDecimal accountBalance = BigDecimal.ZERO;
	
	@Enumerated(EnumType.STRING)
	private CurrencyUnit currencyUnit;
	
	private CustomerResponseDto customerResponseDto;
	private CreditCardResponseDto creditCardResponseDto; 
}
