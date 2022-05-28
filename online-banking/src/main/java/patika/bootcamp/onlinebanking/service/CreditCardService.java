package patika.bootcamp.onlinebanking.service;

import java.util.Date;
import java.util.List;
import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.model.card.CreditCard;

public interface CreditCardService extends BaseService<CreditCard>{
	
	CreditCard findByCustomer_Id(Long customerId);
	List<CreditCard> findByAccountCutOffDate(Date accountCutOffDate);
}
