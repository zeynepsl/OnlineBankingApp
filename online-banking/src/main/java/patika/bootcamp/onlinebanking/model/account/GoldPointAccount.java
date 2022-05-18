package patika.bootcamp.onlinebanking.model.account;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Setter;
import patika.bootcamp.onlinebanking.model.card.Card;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import lombok.Getter;


// The customer earns gold points when he/she makes a purchase from his/her primary account.

@Entity
@Getter
@Setter
public class GoldPointAccount extends Card{
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	private Double percentage = 0.1;
}
