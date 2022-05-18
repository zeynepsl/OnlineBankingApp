package patika.bootcamp.onlinebanking.dto.card;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.account.PrimaryAccountResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;

@Getter
@Setter
public class CreditCardResponseDto {
	
	private String cardNo;
	private BigDecimal accountBalance;
	private CustomerResponseDto customerResponseDto;
	private PrimaryAccountResponseDto primaryAccounResponseDto;
	private BigDecimal additionalLimit;
	private BigDecimal amountOfDebt;
}
