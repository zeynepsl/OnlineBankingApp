package patika.bootcamp.onlinebanking.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.PrepaidCardServiceOperationException;
import patika.bootcamp.onlinebanking.model.card.PrepaidCard;
import patika.bootcamp.onlinebanking.repository.card.PrepaidCardRepository;
import patika.bootcamp.onlinebanking.service.PrepaidCardService;

@Service
@RequiredArgsConstructor
@Transactional
public class PrepaidCardServiceImpl implements PrepaidCardService{
	private final PrepaidCardRepository prepaidCardRepository;
	@Override
	public PrepaidCard create(PrepaidCard prepaidCard) throws BaseException {
		prepaidCard = prepaidCardRepository.save(prepaidCard);
		return prepaidCard;
	}

	@Override
	public PrepaidCard get(Long id) throws BaseException {
		PrepaidCard prepaidCard = prepaidCardRepository.findById(id)
				.orElseThrow(() -> new PrepaidCardServiceOperationException.PrepaidCardNotFound("prepaid card not found"));
		return prepaidCard;
	}

	@Override
	public PrepaidCard update(PrepaidCard prepaidCard) {
		prepaidCard = prepaidCardRepository.save(prepaidCard);
		return prepaidCard;
	}

	@Override
	public void delete(Long id) throws BaseException {
		PrepaidCard prepaidCard = get(id);
		prepaidCard.removeCustomer(prepaidCard.getCustomer());
		prepaidCardRepository.delete(prepaidCard);
	}

	@Override
	public List<PrepaidCard> getAll() {
		return prepaidCardRepository.findAll();
	}

	@Override
	public PrepaidCard findByCustomer_Id(Long customerId) throws BaseException{
		PrepaidCard prepaidCard = prepaidCardRepository.findByCustomer_Id(customerId)
				.orElseThrow(() -> new PrepaidCardServiceOperationException.PrepaidCardNotFound("prepaid card not found"));
		return prepaidCard;
	}

	@Override
	public BigDecimal getBalance(Long prepaidCardId) {
		PrepaidCard prepaidCard = get(prepaidCardId);
		return prepaidCard.getBalance();
	}
	
	
}
