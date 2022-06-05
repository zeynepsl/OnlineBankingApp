package patika.bootcamp.onlinebanking.service.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.card.PrepaidCardConverter;
import patika.bootcamp.onlinebanking.dto.card.CreatePrepaidCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.PrepaidCardResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.card.PrepaidCard;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.service.CustomerService;
import patika.bootcamp.onlinebanking.service.PrepaidCardService;
import patika.bootcamp.onlinebanking.service.facade.PrepaidCardFacade;
import patika.bootcamp.onlinebanking.util.generate.CardNumberGenerator;

@Service
@RequiredArgsConstructor
public class PrepaidCardFacadeImpl implements PrepaidCardFacade {
	private final PrepaidCardService prepaidCardService;
	private final CustomerService customerService;
	private final PrepaidCardConverter prepaidCardConverter;
	private final PasswordEncoder passwordEncoder;
	

	@Override
	public ResponseEntity<PrepaidCardResponseDto> create(CreatePrepaidCardRequestDto createPrepaidCardRequestDto)
			throws BaseException {
		PrepaidCard prepaidCard = prepaidCardConverter.toPrepaidCard(createPrepaidCardRequestDto);
		prepaidCard.setPassword(passwordEncoder.encode(createPrepaidCardRequestDto.getPassword()));
		/*TO DO:
		 * burada customerService e bagli olmamin sebebi Customer nesnesinin fieldlarina ihtiyacim olmasi.
		 * converter da sunu yapiyordum:
		 * Customer c = new Customer();
		 * c.setId(createPrepaidCardRequestDto.getCustomerId())
		 * prepaidCard.setCustomer(c);
		 * 
		 * bu yontem gayet iyi calisiyor fakat customer i sadece id si ile getiriyor, fakat benim customer in icindeki diger alanlara da ihtiyacim var.
		 * FETCH_TYPE=EAGER ile de cozemedim, arastir*/
		Customer customer = customerService.get(createPrepaidCardRequestDto.getCustomerId());
		prepaidCard.setCustomer(customer);
		
		List<Account> accounts = customer.getAccounts().stream().filter(
				a -> a.getAccountType() == AccountType.CHECKING_ACCOUNT && a.getCurrency().getCode().equals("TRY"))
				.collect(Collectors.toList());
		prepaidCard.setCardNumber(CardNumberGenerator.generate(accounts.get(0).getBranch().getBranchCode(), accounts.get(0).getAccountNumber()));
		
		prepaidCard = prepaidCardService.create(prepaidCard);
		return new ResponseEntity<>(prepaidCardConverter.toPrepaidCardResponseDto(prepaidCard), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<PrepaidCardResponseDto> get(Long id) throws BaseException {
		PrepaidCard prepaidCard = prepaidCardService.get(id);
		return ResponseEntity.ok(prepaidCardConverter.toPrepaidCardResponseDto(prepaidCard));
	}

	@Override
	public ResponseEntity<PrepaidCardResponseDto> update(PrepaidCard prepaidCard) {
		prepaidCard = prepaidCardService.update(prepaidCard);
		return ResponseEntity.ok(prepaidCardConverter.toPrepaidCardResponseDto(prepaidCard));
	}

	@Override
	public ResponseEntity<?> delete(Long id) throws BaseException {
		prepaidCardService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<PrepaidCardResponseDto>> getAll() {
		List<PrepaidCard> prepaidCards = prepaidCardService.getAll();
		return ResponseEntity.ok(toPrepaidCardResponseDtoList(prepaidCards));
	}

	public List<PrepaidCardResponseDto> toPrepaidCardResponseDtoList(List<PrepaidCard> prepaidCards) {
		List<PrepaidCardResponseDto> prepaidCardResponseDtos = new ArrayList<PrepaidCardResponseDto>();
		prepaidCards.forEach(prepaidCard -> {
			PrepaidCardResponseDto prepaidCardResponseDto = prepaidCardConverter.toPrepaidCardResponseDto(prepaidCard);
			prepaidCardResponseDtos.add(prepaidCardResponseDto);
		});
		return prepaidCardResponseDtos;
	}

	@Override
	public ResponseEntity<PrepaidCardResponseDto> findByCustomer_Id(Long customerId) throws BaseException {
		PrepaidCard prepaidCard = prepaidCardService.findByCustomer_Id(customerId);
		return ResponseEntity.ok(prepaidCardConverter.toPrepaidCardResponseDto(prepaidCard));
	}

	@Override
	public ResponseEntity<BigDecimal> getBalance(Long prepaidCardId) {
		PrepaidCard prepaidCard = prepaidCardService.get(prepaidCardId);
		return ResponseEntity.ok(prepaidCard.getBalance());
	}
}
