package patika.bootcamp.onlinebanking.model.customer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.customvalidation.PhoneNumber;
import patika.bootcamp.onlinebanking.model.BaseExtendedModel;
import patika.bootcamp.onlinebanking.model.account.GoldPointAccount;
import patika.bootcamp.onlinebanking.model.account.PrimaryAccount;
import patika.bootcamp.onlinebanking.model.account.SavingsAccount;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.model.card.PrepaidCard;

@Entity
@Getter
@Setter
@Validated
public class Customer extends BaseExtendedModel{
	private String email;
	private String identityNumber;
	
	private String phoneNumber;
	private String password;
	
	private Integer age;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private boolean isActive = true;
	private boolean isConfirmedByAdmin = false;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PrimaryAccount> primaryAccounts = new HashSet<PrimaryAccount>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SavingsAccount> savingsAccounts = new HashSet<SavingsAccount>();
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private CreditCard creditCard;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private PrepaidCard prepaidCard;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private GoldPointAccount goldPointAccount;
	
}
