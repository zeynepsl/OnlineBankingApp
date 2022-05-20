package patika.bootcamp.onlinebanking.dto.account;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.account.CurrencyUnit;

@Getter
@Setter
public class SavingsAccountResponseDto {
	private Long id;
	private String accountNumber;
	private BigDecimal accountBalance = BigDecimal.ZERO;
	private CurrencyUnit currencyUnit;
	private CustomerResponseDto customerResponseDto;
	private boolean isActive;
}
