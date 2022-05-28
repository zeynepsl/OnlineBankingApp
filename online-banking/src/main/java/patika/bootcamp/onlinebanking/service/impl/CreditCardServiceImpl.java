package patika.bootcamp.onlinebanking.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.CreditCardServiceOperationException;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.repository.card.CreditCardRepository;
import patika.bootcamp.onlinebanking.service.CreditCardService;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService{
	private final CreditCardRepository creditCardRepository;

	@Override
	public CreditCard create(CreditCard creditCard) throws BaseException {
		creditCard = creditCardRepository.save(creditCard);
		return creditCard;
	}

	@Override
	public CreditCard get(Long id) throws BaseException {
		CreditCard creditCard = creditCardRepository.findById(id)
				.orElseThrow(() -> new CreditCardServiceOperationException.CreditCardNotFound("Credit card not found"));
		return creditCard;
	}

	@Override
	public CreditCard update(CreditCard creditCard) {
		creditCard = creditCardRepository.save(creditCard);
		return creditCard;
	}

	@Override
	public void delete(Long id) throws BaseException {
		CreditCard creditCard = get(id);
		creditCardRepository.delete(creditCard);
	}

	@Override
	public List<CreditCard> getAll() {
		return creditCardRepository.findAll();
	}

	@Override
	public CreditCard findByCustomer_Id(Long customerId) {
		CreditCard creditCard = creditCardRepository.findByCustomer_Id(customerId)
				.orElseThrow(() -> new CreditCardServiceOperationException.CreditCardNotFound("Credit card not found"));
		return creditCard;
	}

	@Override
	public List<CreditCard> findByAccountCutOffDate(Date accountCutOffDate) {
		return creditCardRepository.findByAccountCutOffDate(accountCutOffDate);
	}
	
	
}
