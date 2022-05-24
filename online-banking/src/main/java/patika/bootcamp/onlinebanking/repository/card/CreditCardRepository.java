package patika.bootcamp.onlinebanking.repository.card;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.card.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{
	
	Optional<CreditCard> findByCustomer_Id(Long customerId);

}
