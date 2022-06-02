package patika.bootcamp.onlinebanking.model.customer;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.base.Address;
import patika.bootcamp.onlinebanking.model.enums.AdressType;

@Entity
@Getter
@Setter
public class CustomerAddress extends Address{
	
	@Enumerated(EnumType.STRING)
	private AdressType addressType;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
}
