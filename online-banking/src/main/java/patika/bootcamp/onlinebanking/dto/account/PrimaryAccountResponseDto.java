package patika.bootcamp.onlinebanking.dto.account;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.account.CurrencyUnit;

@Getter
@Setter
public class PrimaryAccountResponseDto {
	
	private String accountNumber;
	private BigDecimal accountBalance;
	private CurrencyUnit currencyUnit;
	private CustomerResponseDto customerResponseDto;
	private boolean isActive;
}
