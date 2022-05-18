package patika.bootcamp.onlinebanking.dto.transaction;

import java.math.BigDecimal;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.account.CurrencyUnit;
import patika.bootcamp.onlinebanking.model.transaction.ModeOfPayment;

@Getter
@Setter
public class CreateTransactionByCardRequestDto {
	private String senderAccountNumber;
	
	@Enumerated(EnumType.STRING)
	private CurrencyUnit senderCurrencyUnit;
	
	private String recipientFirstName;
	private String recipientLastName;
	private String recipientCardNo;
	
	private Boolean useAllBalance = false;
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	private ModeOfPayment modeOfPayment;

}
