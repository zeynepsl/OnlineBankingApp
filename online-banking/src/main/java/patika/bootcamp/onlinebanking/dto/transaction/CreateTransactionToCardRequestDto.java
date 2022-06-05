package patika.bootcamp.onlinebanking.dto.transaction;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
public class CreateTransactionToCardRequestDto {
	//kullaniciya, bir karta  transfer imkani sunar, arka planda transaction yine IBAN üzerinden yapilir
	
	@NotBlank
	private String fromAccountNumber;
	
	@NotBlank
	private String toCardNumber;
	
	@NotNull
	private BigDecimal amount;
	
	@NotNull
	private ModeOfPayment modeOfPayment;
}
