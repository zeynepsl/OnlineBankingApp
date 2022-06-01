package patika.bootcamp.onlinebanking.dto.transaction;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
public class CreateTransactionToCardRequestDto {
	private String fromAccountNumber;
	private String toCardNumber;
	private BigDecimal amount;
	private ModeOfPayment modeOfPayment;
}
