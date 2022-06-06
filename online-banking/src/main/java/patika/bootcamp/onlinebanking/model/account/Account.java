package patika.bootcamp.onlinebanking.model.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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
public class Account extends BaseExtendedModel {

	//accountNumber --> branchCode + customerNumber + additional_account_number
	private String accountName;
	private String accountNumber;
	private String additionalAccountNumber;

	@PositiveOrZero
	private BigDecimal lockedBalance = BigDecimal.ZERO;
	private BigDecimal accountBalance = BigDecimal.ZERO;

	@Column(length = 5)
	@Size(min = 5, max = 5)
	private String bankCode;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "branch_id")
	private Branch branch;
	
	public void setBranch(Branch branch) {
		this.branch = branch;
		if(branch != null) {
			branch.addAccount(this);
		}
	}
	
	public Account removeBranch(Branch branch) {
		this.setBranch(null);
		return this;
	}
	
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

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		if(customer != null) {
			customer.addAccount(this);
		}
	}
	
	public Account removeCustomer(Customer customer) {
		customer.removeAccount(this);
		this.setCustomer(null);
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
	public Account removeCurrency(Currency currency) {
		this.setCurrency(null);
		return this;
	}
	
	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private BankCard bankCard;

	public Account addBankCard(BankCard bankCard) {
		BankCard oldCard = this.bankCard;
		this.bankCard = bankCard;
		if (oldCard != null)
			oldCard.setAccount(null);
		if (bankCard != null)
			bankCard.setAccount(this);
		return this;
	}
	
	public void removeBankCard(BankCard bankCard) {
		this.setBankCard(null);
	}

	@OneToMany(mappedBy = "senderAccount", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private Set<Transaction> transactions = new HashSet<>();

}
