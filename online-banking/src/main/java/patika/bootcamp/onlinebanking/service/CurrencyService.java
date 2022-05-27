package patika.bootcamp.onlinebanking.service;

import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Currency;

public interface CurrencyService extends BaseService<Currency>{
	
	Currency findByName(String name) throws BaseException;
	Currency findByCode(String code) throws BaseException;
	Currency findBySymbol(String symbol) throws BaseException;
	boolean existsByName(String name);
}
