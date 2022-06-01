package patika.bootcamp.onlinebanking.model.transaction;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.model.base.BaseModel;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Entity
@Getter
@Setter
public class Transaction extends BaseModel{
	
	@ManyToOne
	@JoinColumn(name = "sender_currency_id")
	private Currency senderCurrency;
	private String senderIbanNo;
	
	@ManyToOne
	@JoinColumn(name = "sender_account_id")
	private Account senderAccount; 
	
	private String recipientIbanNo;
	
	private Boolean useAllBalance = false;
	private BigDecimal amount;
	
	@Temporal(TemporalType.DATE)
	private Date transactionDate = new Date();
	
	@Enumerated(EnumType.STRING)
	private ModeOfPayment modeOfPayment;
 	
}
