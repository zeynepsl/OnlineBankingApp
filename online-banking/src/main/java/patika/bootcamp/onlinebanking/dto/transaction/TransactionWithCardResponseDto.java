package patika.bootcamp.onlinebanking.dto.transaction;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
public class TransactionWithCardResponseDto {
	private Long id;
	private String senderCustomerNUmber;
	
	private String recipientIbanNo;
	private Date transactionDate;
	private Boolean useAllBalance;
	private BigDecimal amount;
	private ModeOfPayment modeOfPayment;
}
