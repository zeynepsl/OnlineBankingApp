package patika.bootcamp.onlinebanking.repository.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.account.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long>{	
	Optional<Currency> findByName(String name);
	Optional<Currency> findByCode(String code);
	Optional<Currency> findBySymbol(String symbol);
	
	boolean existsByName(String name);
}
