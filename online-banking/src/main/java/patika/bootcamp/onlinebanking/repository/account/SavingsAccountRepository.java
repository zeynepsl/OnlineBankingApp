package patika.bootcamp.onlinebanking.repository.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.account.CurrencyUnit;
import patika.bootcamp.onlinebanking.model.account.SavingsAccount;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long>{
	
	List<SavingsAccount> findByCustomer_Id(Long customerId);
	
	Optional<SavingsAccount> findByAccountNumber(String accountNumber);
	
	List<SavingsAccount> findByCurrencyUnit(CurrencyUnit currencyUnit);
}
