package patika.bootcamp.onlinebanking.dto.transaction;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.dto.response.CurrencyResponseDto;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
public class TransactionByIbanResponseDto {
	private Long id;
	private String senderAccountNumber;
	private CurrencyResponseDto senderCurrencyResponseDto;
	
	private String recipientFirstName;
	private String recipientLastName;
	private String recipientIbanNo;
	
	private Date transactionDate;
	private Boolean useAllBalance;
	private BigDecimal amount;
	
	private ModeOfPayment modeOfPayment;
}
