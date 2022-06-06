package patika.bootcamp.onlinebanking.repository.transaction;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;
import patika.bootcamp.onlinebanking.model.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findByTransactionDate(Date transacionDate);
	
	List<Transaction> findByModeOfPayment(ModeOfPayment modeOfPayment);
	
	List<Transaction> findBySenderIbanNo(String ibanNo);
	
	List<Transaction> findByRecipientIbanNo(String ibanNo);
	
	List<Transaction> findBySenderAccount_Id(Long id);
	
	List<Transaction> findByTransactionDateBetweenAndSenderCustomerNumber(Date startDate, Date endDate, String senderCustomerNumber);
}
