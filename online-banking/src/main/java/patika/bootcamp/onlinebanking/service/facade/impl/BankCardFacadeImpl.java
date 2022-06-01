package patika.bootcamp.onlinebanking.service.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.BankCardConverter;
import patika.bootcamp.onlinebanking.dto.card.BankCardResponseDto;
import patika.bootcamp.onlinebanking.dto.card.CreateBankCardRequestDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.service.BankCardService;
import patika.bootcamp.onlinebanking.service.facade.BankCardFacade;

@Service
@RequiredArgsConstructor
public class BankCardFacadeImpl implements BankCardFacade{
	private final BankCardService bankCardService;
	private final BankCardConverter bankCardConverter;

	@Override
	public ResponseEntity<BankCardResponseDto> create(CreateBankCardRequestDto createBankCardRequestDto)
			throws BaseException {
		BankCard bankCard = bankCardConverter.toBankCard(createBankCardRequestDto);
		bankCardService.create(bankCard);
		return new ResponseEntity<>(bankCardConverter.toBankCardResponseDto(bankCard), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<BankCardResponseDto> get(Long id) throws BaseException {
		BankCard bankCard = bankCardService.get(id);
		return ResponseEntity.ok(bankCardConverter.toBankCardResponseDto(bankCard));
	}

	@Override
	public ResponseEntity<BankCardResponseDto> update(CreateBankCardRequestDto createBankCardRequestDto) {
		BankCard bankCard = bankCardConverter.toBankCard(createBankCardRequestDto);
		bankCardService.create(bankCard);
		return ResponseEntity.ok(bankCardConverter.toBankCardResponseDto(bankCard));
	}

	@Override
	public ResponseEntity<?> delete(Long id) throws BaseException {
		bankCardService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<BankCardResponseDto>> getAll() {
		List<BankCard> bankCards = bankCardService.getAll();
		return ResponseEntity.ok(toBankCardResponseDtoList(bankCards));
	}

	public List<BankCardResponseDto> toBankCardResponseDtoList(List<BankCard> bankCards) {
		List<BankCardResponseDto> bankCardResponseDtos = new ArrayList<BankCardResponseDto>();
		bankCards.forEach(bankCard -> {
			BankCardResponseDto bankCardResponseDto = bankCardConverter.toBankCardResponseDto(bankCard);
			bankCardResponseDtos.add(bankCardResponseDto);
		});
		return bankCardResponseDtos;
	}

	@Override
	public ResponseEntity<BankCardResponseDto> findByCustomerId(Long customerId) {
		BankCard bankCard = bankCardService.findByCustomerId(customerId);
		return ResponseEntity.ok(bankCardConverter.toBankCardResponseDto(bankCard));
	}

	@Override
	public ResponseEntity<BankCardResponseDto> findByAccountId(Long accountId) {
		BankCard bankCard = bankCardService.findByAccountId(accountId);
		return ResponseEntity.ok(bankCardConverter.toBankCardResponseDto(bankCard));
	}

	@Override
	public ResponseEntity<BigDecimal> getAccountBalance(Long bankCardId) {
		BigDecimal balance = bankCardService.getAccountBalance(bankCardId);
		return ResponseEntity.ok(balance);
	}

	@Override
	public ResponseEntity<?> withdraw(BankCard bankCard, String password, BigDecimal amount) {
		bankCardService.withdraw(bankCard, password, amount);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> deposit(BankCard bankCard, String password, BigDecimal amount) {
		bankCardService.deposit(bankCard, password, amount);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<String> getIban(BankCard bankCard, String password) throws BaseException {
		String iban = bankCardService.getIban(bankCard, password);
		return ResponseEntity.ok(iban);
	}

	@Override
	public ResponseEntity<BankCardResponseDto> findByCardNumber(String cardNumber) {
		BankCard bankCard = bankCardService.findByCardNumber(cardNumber);
		return ResponseEntity.ok(bankCardConverter.toBankCardResponseDto(bankCard));
	}
}
