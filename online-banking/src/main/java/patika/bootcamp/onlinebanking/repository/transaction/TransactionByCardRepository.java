package patika.bootcamp.onlinebanking.repository.transaction;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.transaction.ModeOfPayment;
import patika.bootcamp.onlinebanking.model.transaction.TransactionByCard;

public interface TransactionByCardRepository extends JpaRepository<TransactionByCard, Long>{
	List<TransactionByCard> findByTransactionDate(Date transacionDate);
	
	List<TransactionByCard> findByModeOfPayment(ModeOfPayment modeOfPayment);
	
	List<TransactionByCard> findBySenderAccountNumber(String senderAccountNumber);
	
	List<TransactionByCard> findByRecipientCardNo(String recipientCardNo);
}
