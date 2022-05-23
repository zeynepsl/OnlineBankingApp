package patika.bootcamp.onlinebanking.model.card;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.account.PrimaryAccount;
import patika.bootcamp.onlinebanking.model.customer.Customer;

@Entity
@Getter
@Setter
public class CreditCard extends Card{
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name = "primary_account_id")
	private PrimaryAccount primaryAccount;
	
	private BigDecimal limit;
	private BigDecimal availableLimit;// availableLimit = limit - periodExpenditures
	private BigDecimal periodExpenditures;
	
	@Temporal(TemporalType.DATE)
	private Date accountCutOffDate;//hesap kesim tarihi
	
	@Temporal(TemporalType.DATE)
	private Date paymentDueDate;//son odeme tarihi
	//private BigDecimal additionalLimit = BigDecimal.valueOf(250);//It can be 250, 500, 1.000, 1.500 or 2.000.
	private BigDecimal amountOfDebt;
}
