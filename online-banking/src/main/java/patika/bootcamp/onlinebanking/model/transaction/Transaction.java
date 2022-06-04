package patika.bootcamp.onlinebanking.model.transaction;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.base.BaseModel;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@Entity
@Getter
@Setter
public class Transaction extends BaseModel{
	
	private String senderIbanNo;
	private String senderCustomerNumber;//bir hesaba bagli olmayan kredi kartiyla transferde, gonderen tarafini belirtir.
	
	@ManyToOne
	@JoinColumn(name = "sender_account_id")
	private Account senderAccount; 
	
	private String recipientIbanNo;
	
	private Boolean useAllBalance = false;
	private BigDecimal amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate = new Date();
	
	@Enumerated(EnumType.STRING)
	private ModeOfPayment modeOfPayment;
 	
}
