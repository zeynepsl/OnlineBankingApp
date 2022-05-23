package patika.bootcamp.onlinebanking.model.account;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.model.customer.Customer;

@Entity
@Getter
@Setter
public class PrimaryAccount extends Account{
	
	@ManyToOne
	@JoinColumn(name =  "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
	@OneToOne(mappedBy = "primaryAccount", cascade = CascadeType.ALL, orphanRemoval = true)
	private CreditCard creditCard;
}
