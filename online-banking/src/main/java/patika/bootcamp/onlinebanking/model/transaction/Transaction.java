package patika.bootcamp.onlinebanking.model.transaction;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.BaseModel;
import patika.bootcamp.onlinebanking.model.account.CurrencyUnit;

@Getter
@Setter
@MappedSuperclass
public abstract class Transaction extends BaseModel{
	private String senderAccountNumber;
	
	@Enumerated(EnumType.STRING)
	private CurrencyUnit senderCurrencyUnit;
	//private BigDecimal senderAccountBalance;//bunu accountNUmber dan da alabiliriz
	
	private String recipientFirstName;
	private String recipientLastName;
	
	@Temporal(TemporalType.DATE)
	private Date transacionDate = new Date();
	
	private Boolean useAllBalance = false;
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	private ModeOfPayment modeOfPayment;
}
