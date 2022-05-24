package patika.bootcamp.onlinebanking.repository.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountType;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByAccountNumber(Long accountNumber);
	Optional<Account> findByIban(String iban);
	
	List<Account> findByCustomer_Id(Long customerId);
	List<Account> findByCurrency_Id(Long currencyId);
	List<Account> findByAccountType(AccountType accountType);
	List<Account> findByBankCode(String bankCode);
	List<Account> findByBranchCode(String branchCode);
	
}
