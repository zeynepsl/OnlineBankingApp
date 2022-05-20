package patika.bootcamp.onlinebanking.repository.transaction;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.transaction.ModeOfPayment;
import patika.bootcamp.onlinebanking.model.transaction.TransactionByIban;

public interface TransactionByIbanRepository extends JpaRepository<TransactionByIban, Long> {
	List<TransactionByIban> findByTransactionDate(Date transacionDate);

	List<TransactionByIban> findByModeOfPayment(ModeOfPayment modeOfPayment);

	List<TransactionByIban> findBySenderAccountNumber(String senderAccountNumber);
	
	List<TransactionByIban> findByRecipientIbanNo(String recipientIbanNo);
}
