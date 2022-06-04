package patika.bootcamp.onlinebanking.model.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.base.BaseExtendedModel;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.model.transaction.Transaction;

@Entity
@Getter
@Setter
public class Account extends BaseExtendedModel{
	
	//private Long accountNumber --> branchCode + customerNumber + additional_account_number
	private String accountName;
	private String accountNumber;
	private String additionalAccountNumber;
	
	@PositiveOrZero
	private BigDecimal lockedBalance = BigDecimal.ZERO;
	private BigDecimal accountBalance = BigDecimal.ZERO;
	
	@Column(length = 5)
	@Size(min = 5, max = 5)
	private String bankCode;
	
	@ManyToOne(cascade = CascadeType.ALL)//merge den all 
	@JoinColumn(name = "branch_id")
	private Branch branch;
	
	private String iban;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date blockedAt;
	
	@FutureOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	private Date canBeActiveAt;
	
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus = AccountStatus.PASSIVE;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType = AccountType.CHECKING_ACCOUNT;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name =  "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
	@OneToOne(mappedBy = "account", orphanRemoval = true)
	private BankCard bankCard;
	
	@OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
	private Set<Transaction> transactions = new HashSet<>();
	
}
