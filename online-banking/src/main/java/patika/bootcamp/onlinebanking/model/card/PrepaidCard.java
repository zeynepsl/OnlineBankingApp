package patika.bootcamp.onlinebanking.model.card;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.customer.Customer;

@Entity
@Getter
@Setter
public class PrepaidCard extends Card{
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	private BigDecimal minBalance;
	private BigDecimal maxBalance;
	
}
