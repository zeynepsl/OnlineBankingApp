package patika.bootcamp.onlinebanking.model.bank;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.base.BaseModel;

@Entity
@Getter
@Setter
public class BankBranch extends BaseModel{
	private String branchName;
	private String branchCode;
	
	@OneToOne
	@JoinColumn(name = "bank_branch_address_id")
	private BankBranchAddress bankBranchAddress;
	
	@OneToMany(mappedBy = "bankBranch", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Account> accounts = new HashSet<>();
}
