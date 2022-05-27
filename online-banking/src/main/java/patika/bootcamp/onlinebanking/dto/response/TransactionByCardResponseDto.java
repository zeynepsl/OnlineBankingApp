package patika.bootcamp.onlinebanking.dto.response;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
public class TransactionByCardResponseDto {
	private Long id;
	private String senderAccountNumber;
	private CurrencyResponseDto senderCurrencyResponseDto;
	
	private String recipientFirstName;
	private String recipientLastName;
	private String recipientCardNo;
	
	private Date transactionDate;
	private Boolean useAllBalance;
	private BigDecimal amount;
	
	private ModeOfPayment modeOfPayment;
}
