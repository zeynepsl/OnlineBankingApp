package patika.bootcamp.onlinebanking.repository.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.model.account.PrimaryAccount;

public interface PrimaryAccountRepository extends JpaRepository<PrimaryAccount, Long>{
	
	List<PrimaryAccount> findByCustomer_Id(Long customerId);
	
	Optional<PrimaryAccount> findByCreditCard_Id(Long creditCardId);
	Optional<PrimaryAccount> findByAccountNumber(String accountNumber);
	
	List<PrimaryAccount> findByCurrency_Id(Long currencyId);
}
