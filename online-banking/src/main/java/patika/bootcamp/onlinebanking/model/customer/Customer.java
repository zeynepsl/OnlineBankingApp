package patika.bootcamp.onlinebanking.model.customer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Past;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.base.BaseExtendedModel;
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
	private String password;
	private Integer age;
	
	@Past
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private boolean isActive = true;
	private boolean isConfirmedByAdmin = false;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Address> customerAddresses = new HashSet<>();
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Account> accounts = new HashSet<>();
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private CreditCard creditCard;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private PrepaidCard prepaidCard;
	
	@Transient
	private String getFullName() {
		return firstName + " " + lastName;
	}
	
}
