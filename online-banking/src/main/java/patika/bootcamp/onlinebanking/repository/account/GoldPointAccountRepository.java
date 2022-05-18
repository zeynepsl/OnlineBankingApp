package patika.bootcamp.onlinebanking.repository.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.account.CurrencyUnit;
import patika.bootcamp.onlinebanking.model.account.GoldPointAccount;

public interface GoldPointAccountRepository extends JpaRepository<GoldPointAccount, Long>{
	Optional<GoldPointAccount> findByCustomer_Id(Long customerId);
	
	Optional<GoldPointAccount> findByAccountNumber(String accountNumber);
	
	List<GoldPointAccount> findByCurrencyUnit(CurrencyUnit currencyUnit);
}
