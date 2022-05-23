package patika.bootcamp.onlinebanking.dto.request;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
public class CreateTransactionByIbanRequestDto {
	private String senderAccountNumber;
	
	private Long senderCurrencyId;
	
	private String recipientFirstName;
	private String recipientLastName;
	private String recipientIbanNo;
	
	private Boolean useAllBalance = false;
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	private ModeOfPayment modeOfPayment;
}
