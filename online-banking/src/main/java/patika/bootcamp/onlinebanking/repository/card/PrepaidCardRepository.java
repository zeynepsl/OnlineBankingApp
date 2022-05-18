package patika.bootcamp.onlinebanking.repository.card;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.card.PrepaidCard;

public interface PrepaidCardRepository extends JpaRepository<PrepaidCard, Long>{
	Optional<PrepaidCard> findByCustomer_Id(Long customerId);
}
