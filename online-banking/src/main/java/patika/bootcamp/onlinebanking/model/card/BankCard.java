package patika.bootcamp.onlinebanking.model.card;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.base.Card;
import patika.bootcamp.onlinebanking.model.customer.Customer;

@Entity
@Getter
@Setter
public class BankCard extends Card{
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public BankCard removeCustomer() {
		this.setCustomer(null);
		return this;
	}
	
	@OneToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	public BankCard removeAccount() {
		this.setAccount(null);
		return this;
	}
}
