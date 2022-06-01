package patika.bootcamp.onlinebanking.dto.card;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;

@Getter
@Setter
public class PrepaidCardResponseDto {
	private Long id;
	private String cardNumber;
	private Boolean isActive;
	private BigDecimal accountBalance;
	private CustomerResponseDto customerResponseDto;
	private BigDecimal minBalance = BigDecimal.valueOf(300);
	private BigDecimal maxBalance = BigDecimal.valueOf(1000);
}
