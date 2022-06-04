package patika.bootcamp.onlinebanking.model.customer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Past;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.User;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.base.BaseExtendedModel;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.model.card.PrepaidCard;
import patika.bootcamp.onlinebanking.model.enums.Gender;

@Entity
@Getter
@Setter  
@Validated
public class Customer extends BaseExtendedModel{
	
	@Embedded
	private ContactInformation contactInformation;
	
	private String firstName;
	private String lastName;
	private Long identityNumber;
	private Integer age;
	private String customerNumber;
	
	@Past
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private boolean isActive = true;
	private boolean isConfirmedByAdmin = false;
	
	@OneToMany(mappedBy = "customer")
	private Set<CustomerAddress> customerAddresses = new HashSet<>();
	
	//fetch = FetchType.EAGER 
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Account> accounts = new HashSet<>();
	
	@OneToOne(mappedBy = "customer", orphanRemoval = true)
	private CreditCard creditCard;
	
	//, cascade = CascadeType.ALL
	@OneToOne(mappedBy = "customer", orphanRemoval = true, fetch = FetchType.EAGER)
	private PrepaidCard prepaidCard;
	
	@OneToOne(mappedBy = "customer", orphanRemoval = true)
	private BankCard bankCard;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Transient
	private String getFullName() {
		return firstName + " " + lastName;
	}
	
}
