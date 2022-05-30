package patika.bootcamp.onlinebanking.service;

import java.math.BigDecimal;

import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.PrepaidCard;

public interface PrepaidCardService extends BaseService<PrepaidCard>{
	PrepaidCard findByCustomer_Id(Long customerId) throws BaseException;
	BigDecimal getBalance(Long prepaidCardId);
}
