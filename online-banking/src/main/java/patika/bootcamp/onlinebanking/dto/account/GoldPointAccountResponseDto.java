package patika.bootcamp.onlinebanking.dto.account;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.dto.response.CurrencyResponseDto;

@Getter
@Setter
public class GoldPointAccountResponseDto {
	private Long id;
	private String accountNumber;
	private BigDecimal accountBalance;
	private CurrencyResponseDto currencyResponseDto;
	
	private CustomerResponseDto customerResponseDto;
	private Double percentage = 0.1;
	private boolean isActive;
}
