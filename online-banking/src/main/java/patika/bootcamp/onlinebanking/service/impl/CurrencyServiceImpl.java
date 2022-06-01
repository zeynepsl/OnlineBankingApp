package patika.bootcamp.onlinebanking.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.CurrencyServiceOperationException;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.repository.account.CurrencyRepository;
import patika.bootcamp.onlinebanking.service.CurrencyService;

@Service
@RequiredArgsConstructor
@Transactional
public class CurrencyServiceImpl implements CurrencyService{

	private final CurrencyRepository currencyRepository;
	
	@Override
	public Currency create(Currency currency) throws BaseException{
		currency = currencyRepository.save(currency);
		return currency;
	}

	@Override
	public Currency get(Long id) throws BaseException {
		Currency currency = currencyRepository.findById(id)
				.orElseThrow(() -> new CurrencyServiceOperationException.CurrencyNotFound(""));
		return currency;
	}

	@Override
	public Currency update(Currency currency) {
		currency = currencyRepository.save(currency);
		return currency;
	}

	@Override
	public void delete(Long id) throws BaseException {
		Currency currency = get(id);
		currencyRepository.delete(currency);
	}
	
	@Override
	public List<Currency> getAll() {
		return currencyRepository.findAll();
	}

	@Override
	public Currency findByName(String name) throws BaseException{
		Currency currency = currencyRepository.findByName(name)
				.orElseThrow(() -> new CurrencyServiceOperationException.CurrencyNotFound("CurrencyNotFound"));
		return currency;
	}

	@Override
	public Currency findByCode(String code) throws BaseException{
		Currency currency = currencyRepository.findByCode(code)
				.orElseThrow(() -> new CurrencyServiceOperationException.CurrencyNotFound("CurrencyNotFound"));
		return currency;
	}

	@Override
	public Currency findBySymbol(String symbol) throws BaseException{
		Currency currency = currencyRepository.findBySymbol(symbol)
				.orElseThrow(() -> new CurrencyServiceOperationException.CurrencyNotFound("CurrencyNotFound"));
		return currency;
	}
	
	@Override
	public boolean existsByName(String name) {
		boolean result = currencyRepository.existsByName(name);
		return result;
	}
	
}
