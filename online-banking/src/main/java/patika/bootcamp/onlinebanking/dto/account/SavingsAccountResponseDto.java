package patika.bootcamp.onlinebanking.dto.account;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.dto.response.CurrencyResponseDto;

@Getter
@Setter
public class SavingsAccountResponseDto {
	private Long id;
	private String accountNumber;
	private BigDecimal accountBalance = BigDecimal.ZERO;
	private CurrencyResponseDto currencyResponseDto;
	private CustomerResponseDto customerResponseDto;
	private boolean isActive;
}
