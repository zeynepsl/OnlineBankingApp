package patika.bootcamp.onlinebanking.dto.transaction;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
public class CreateTransactionRequestDto {
	@NotBlank
	private String senderIbanNo;
	
	@NotBlank
	private String recipientIbanNo;
	
	private Boolean useAllBalance = false;
	
	@NotNull
	private BigDecimal amount;
	
	@NotNull
	private ModeOfPayment modeOfPayment;

}
