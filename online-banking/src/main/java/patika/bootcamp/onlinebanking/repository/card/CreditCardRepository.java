package patika.bootcamp.onlinebanking.repository.card;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.card.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{
	
	Optional<CreditCard> findByCustomer_Id(Long customerId);
	List<CreditCard> findByAccountCutOffDate(Date accountCutOffDate);
	Optional<CreditCard> findByCardNumber(String cardNumber);

}
