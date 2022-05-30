package patika.bootcamp.onlinebanking.repository.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountType;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByAccountNumber(String accountNumber);
	Optional<Account> findByIban(String iban);
	
	List<Account> findByCustomer_Id(Long customerId);
	List<Account> findByCurrency_Id(Long currencyId);
	List<Account> findByAccountType(AccountType accountType);
	List<Account> findByBankCode(String bankCode);
	List<Account> findByBankBranch_BranchCode(String branchCode);
	List<Account> findByBankBranch_BranchName(String branchName);
	List<Account> findByBankBranch_BranchNameAndCustomer_Id(String branchName, Long customerId);
	
}
