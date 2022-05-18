package patika.bootcamp.onlinebanking.dto.transaction;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.account.CurrencyUnit;
import patika.bootcamp.onlinebanking.model.transaction.ModeOfPayment;

@Getter
@Setter
public class TransactionByCardResponseDto {
	private String senderAccountNumber;
	private CurrencyUnit senderCurrencyUnit;
	
	private String recipientFirstName;
	private String recipientLastName;
	private String recipientCardNo;
	
	private Date transactionDate;
	private Boolean useAllBalance;
	private BigDecimal amount;
	
	private ModeOfPayment modeOfPayment;
}
