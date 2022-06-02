package patika.bootcamp.onlinebanking.model.card;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.base.Card;
import patika.bootcamp.onlinebanking.model.customer.Customer;

@Entity
@Getter
@Setter
public class CreditCard extends Card{
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "bank_branch_id")
	private Branch bankBranch;
	
	private BigDecimal cardLimit;//7_000
	private BigDecimal availableLimit;// availableLimit = limit - periodExpenditures  2500
	private BigDecimal periodExpenditures;// 4500 tl harcamisim
	
	@Temporal(TemporalType.DATE)
	private Date accountCutOffDate;//hesap kesim tarihi
	
	@Temporal(TemporalType.DATE)
	private Date paymentDueDate;//son odeme tarihi
	//private BigDecimal additionalLimit = BigDecimal.valueOf(250);//It can be 250, 500, 1.000, 1.500 or 2.000.
	private BigDecimal amountOfDebt;
	
	@Size(min = 3, max = 3)
	private String cvv;
	
	@Temporal(TemporalType.DATE)
	private Date dueDate;
}