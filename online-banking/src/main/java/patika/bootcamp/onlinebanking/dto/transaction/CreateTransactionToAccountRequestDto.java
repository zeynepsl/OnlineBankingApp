package patika.bootcamp.onlinebanking.dto.transaction;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
public class CreateTransactionToAccountRequestDto {
	//kullaniciya, bir hesaba transfer imkani sunar, arka planda transaction yine IBAN üzerinden yapilir
	
	@NotBlank
	private String fromAccountNumber;
	
	@NotBlank
	private String toAccountNumber;
	
	@NotNull
	private BigDecimal amount;
	
	@NotNull
	private ModeOfPayment modeOfPayment;
}
