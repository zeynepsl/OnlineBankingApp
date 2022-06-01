package patika.bootcamp.onlinebanking.dto.transaction;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
public class CreateTransactionToAccountRequestDto {
	private String fromAccountNumber;
	private String toAccountNumber;
	private BigDecimal amount;
	private ModeOfPayment modeOfPayment;
}
