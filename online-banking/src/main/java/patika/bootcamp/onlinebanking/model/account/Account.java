package patika.bootcamp.onlinebanking.model.account;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.bank.BankBranch;
import patika.bootcamp.onlinebanking.model.base.BaseExtendedModel;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;

@Entity
@Getter
@Setter
public class Account extends BaseExtendedModel{
	
	//private Long accountNumber;//branchCode + customerNumber + additional_account_number
	private String accountNumber;
	private String additionalAccountNumber;
	
	@PositiveOrZero
	private BigDecimal lockedBalance = BigDecimal.ZERO;
	private BigDecimal accountBalance = BigDecimal.ZERO;
	
	@Column(length = 5)
	@Size(min = 5, max = 5)
	private String bankCode;
	
	//private String branchCode;
	@ManyToOne
	@JoinColumn(name = "bank_branch_id")
	private BankBranch bankBranch;
	
	//@Pattern(regexp = "")
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
	
	@ManyToOne
	@JoinColumn(name =  "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private BankCard bankCard;
	
	/*@Transient
	private String getAccountNumber() {
		return branchCode + customer.getCustomerNumber() + additionalAccountNumber;
	}*/
	
	//@Enumerated(EnumType.STRING)
	//private CurrencyUnit currencyUnit;
	//private boolean isActive = true; bunu AccountStatus ile yaptık yuk
}
