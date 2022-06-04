package patika.bootcamp.onlinebanking.service.facade.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.card.CreditCardConverter;
import patika.bootcamp.onlinebanking.dto.card.CreateCreditCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreateOnlineTransferByCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreditCardResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.BranchService;
import patika.bootcamp.onlinebanking.service.CreditCardService;
import patika.bootcamp.onlinebanking.service.CustomerService;
import patika.bootcamp.onlinebanking.service.facade.CreditCardFacade;
import patika.bootcamp.onlinebanking.util.generate.CardNumberGenerator;

@Service
@RequiredArgsConstructor
public class CreditCardFacadeImpl implements CreditCardFacade {
	private final AccountService accountService;
	private final CustomerService customerService;
	private final CreditCardService creditCardService;
	private final BranchService branchService;
	private final CreditCardConverter creditCardConverter;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<CreditCardResponseDto> create(CreateCreditCardRequestDto createCreditCardRequestDto)
			throws BaseException {
		CreditCard creditCard = creditCardConverter.toCreditCard(createCreditCardRequestDto);
		creditCard.setPassword(passwordEncoder.encode(createCreditCardRequestDto.getPassword()));

		Customer customer = customerService.get(createCreditCardRequestDto.getCustomerId());
		creditCard.setCustomer(customer);
		
		Branch branch = branchService.get(createCreditCardRequestDto.getBankBranchId());
		creditCard.setBankBranch(branch);
		
		String branchCode = branch.getBranchCode();
		List<Account> accounts = accountService.findByBranchCodeAndCustomerId(branchCode, customer.getId());
		List<Account> tryAccounts = accounts
				.stream()
				.filter(a -> a.getCurrency().getCode().equals("TRY"))
				.collect(Collectors.toList());
		creditCard.setCardNumber(CardNumberGenerator.generate(branchCode, tryAccounts.get(0).getAccountNumber()));
		
		creditCard = creditCardService.create(creditCard);
		return new ResponseEntity<>(creditCardConverter.toCreditCardResponseDto(creditCard), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CreditCardResponseDto> get(Long id) throws BaseException {
		CreditCard creditCard = creditCardService.get(id);
		return ResponseEntity.ok(creditCardConverter.toCreditCardResponseDto(creditCard));
	}

	@Override
	public ResponseEntity<CreditCardResponseDto> update(CreditCard creditCard) {
		creditCard = creditCardService.update(creditCard);
		return ResponseEntity.ok(creditCardConverter.toCreditCardResponseDto(creditCard));
	}

	@Override
	public ResponseEntity<?> delete(Long id) throws BaseException {
		creditCardService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<CreditCardResponseDto>> getAll() {
		List<CreditCard> creditCards = creditCardService.getAll();
		return ResponseEntity.ok(toCreditCardResponseDtoList(creditCards));
	}

	public List<CreditCardResponseDto> toCreditCardResponseDtoList(List<CreditCard> creditCards) {
		List<CreditCardResponseDto> creditCardResponseDtos = new ArrayList<CreditCardResponseDto>();
		creditCards.forEach(creditCard -> {
			CreditCardResponseDto creditCardResponseDto = creditCardConverter.toCreditCardResponseDto(creditCard);
			creditCardResponseDtos.add(creditCardResponseDto);
		});
		return creditCardResponseDtos;
	}

	@Override
	public ResponseEntity<CreditCardResponseDto> findByCustomer_Id(Long customerId) {
		CreditCard creditCard = creditCardService.findByCustomer_Id(customerId);
		return ResponseEntity.ok(creditCardConverter.toCreditCardResponseDto(creditCard));
	}

	@Override
	public ResponseEntity<CreditCardResponseDto> findByCardNumber(String cardNumber) {
		CreditCard creditCard = creditCardService.findByCardNumber(cardNumber);
		return ResponseEntity.ok(creditCardConverter.toCreditCardResponseDto(creditCard));
	}

	@Override
	public ResponseEntity<List<CreditCardResponseDto>> findByAccountCutOffDate(Date accountCutOffDate) {
		List<CreditCard> creditCards = creditCardService.findByAccountCutOffDate(accountCutOffDate);
		return ResponseEntity.ok(toCreditCardResponseDtoList(creditCards));
	}

	@Override
	public ResponseEntity<List<CreditCardResponseDto>> findCardsThatHaveDebt() {
		List<CreditCard> creditCards = creditCardService.findCardsThatHaveDebt();
		return ResponseEntity.ok(toCreditCardResponseDtoList(creditCards));
	}

	@Override
	public ResponseEntity<BigDecimal> getCardLimit(Long creditCardId) {
		CreditCard creditCard = creditCardService.get(creditCardId);
		return ResponseEntity.ok(creditCard.getCardLimit());
	}

	@Override
	public ResponseEntity<BigDecimal> getAvailableLimit(Long creditCardId) {
		CreditCard creditCard = creditCardService.get(creditCardId);
		return ResponseEntity.ok(creditCard.getAvailableLimit());
	}

	@Override
	public ResponseEntity<BigDecimal> getPeriodExpenditures(Long creditCardId) {
		CreditCard creditCard = creditCardService.get(creditCardId);
		return ResponseEntity.ok(creditCard.getPeriodExpenditures());
	}

	@Override
	public ResponseEntity<BigDecimal> getAmountOfDebt(Long creditCardId) {
		CreditCard creditCard = creditCardService.get(creditCardId);
		return ResponseEntity.ok(creditCard.getAmountOfDebt());
	}

	@Override
	public ResponseEntity<?> moneyTransfer(CreditCard creditCard, String password, String to, BigDecimal amount)
			throws BaseException, IOException {
		creditCardService.moneyTransfer(creditCard, password, to, amount);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> onlineMoneyTransfer(CreateOnlineTransferByCardRequestDto onlineTransferByCardRequestDto) throws IOException {
		creditCardService.onlineMoneyTransfer(onlineTransferByCardRequestDto);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> paymentDebtFromCashMachine(CreditCard creditCard, String password) throws BaseException, IOException {
		creditCardService.payDebtFromCashMachine(creditCard, password);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> paymentDebtFromAccount(Long accountId) {
		creditCardService.payDebtFromAccount(accountId);
		return ResponseEntity.ok().build();
	}
}
