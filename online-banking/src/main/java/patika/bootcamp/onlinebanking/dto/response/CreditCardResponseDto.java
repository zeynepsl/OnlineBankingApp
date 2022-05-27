package patika.bootcamp.onlinebanking.dto.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardResponseDto {
	private Long id;
	private String cardNo;
	private BigDecimal accountBalance;
	private CustomerResponseDto customerResponseDto;
	private AccountResponseDto accounResponseDto;
	private BigDecimal additionalLimit;
	private BigDecimal amountOfDebt;
}
