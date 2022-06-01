package patika.bootcamp.onlinebanking.dto.card;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;

@Getter
@Setter
public class BankCardResponseDto {
	private Long id;
	private CustomerResponseDto customerResponseDto;
	private AccountResponseDto accountResponseDto;
	private String cardNumber;
	private Boolean isActive;
}
