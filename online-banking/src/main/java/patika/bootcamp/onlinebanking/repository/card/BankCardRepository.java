package patika.bootcamp.onlinebanking.repository.card;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.card.BankCard;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {
	Optional<BankCard> findByCustomer_Id(Long customerId);
	Optional<BankCard> findByAccount_Id(Long accountId);
	Optional<BankCard> findByCardNumber(String cardNumber);
}
