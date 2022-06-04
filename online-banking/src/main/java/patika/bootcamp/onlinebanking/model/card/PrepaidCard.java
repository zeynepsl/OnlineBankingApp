package patika.bootcamp.onlinebanking.model.card;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.base.Card;
import patika.bootcamp.onlinebanking.model.customer.Customer;

@Entity
@Getter
@Setter
public class PrepaidCard extends Card{
	
	//(fetch = FetchType.EAGER)
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public PrepaidCard removeCustomer(Customer customer) {
		this.setCustomer(null);
		return this;
	}
	private BigDecimal balance = BigDecimal.ZERO;
	
	private BigDecimal minBalance = BigDecimal.valueOf(300);
	private BigDecimal maxBalance = BigDecimal.valueOf(1000);
	
}
