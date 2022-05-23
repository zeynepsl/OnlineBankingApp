package patika.bootcamp.onlinebanking.model.transaction;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.model.base.BaseModel;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Getter
@Setter
@MappedSuperclass
public abstract class Transaction extends BaseModel{
	
	@ManyToOne
	@JoinColumn(name = "sender_currency_id")
	private Currency senderCurrency;
	private String senderAccountNumber;
	//private BigDecimal senderAccountBalance;//bunu accountNUmber dan da alabiliriz
	
	private String recipientFirstName;
	private String recipientLastName;
	
	@Temporal(TemporalType.DATE)
	private Date transactionDate = new Date();
	
	private Boolean useAllBalance = false;
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	private ModeOfPayment modeOfPayment;
}
