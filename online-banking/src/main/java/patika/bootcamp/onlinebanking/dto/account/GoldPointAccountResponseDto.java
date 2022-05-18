package patika.bootcamp.onlinebanking.dto.account;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.account.CurrencyUnit;

@Getter
@Setter
public class GoldPointAccountResponseDto {
	private String accountNumber;
	private BigDecimal accountBalance;
	private CurrencyUnit currencyUnit;
	
	private CustomerResponseDto customerResponseDto;
	private Double percentage = 0.1;
}
