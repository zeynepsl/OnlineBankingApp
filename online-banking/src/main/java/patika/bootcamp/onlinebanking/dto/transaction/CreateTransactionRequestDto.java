package patika.bootcamp.onlinebanking.dto.transaction;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
public class CreateTransactionRequestDto {
	
	private String senderIbanNo;
	private Long senderCurrencyId;
	private String recipientIbanNo;
	private Boolean useAllBalance = false;
	private BigDecimal amount;
	private ModeOfPayment modeOfPayment;

}
