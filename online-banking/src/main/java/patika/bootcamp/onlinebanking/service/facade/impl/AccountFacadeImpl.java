package patika.bootcamp.onlinebanking.service.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.AccountConverter;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.facade.AccountFacade;

@Service
@RequiredArgsConstructor
public class AccountFacadeImpl implements AccountFacade{
	private final AccountService accountService;
	private final AccountConverter accountConverter;

	@Override
	public ResponseEntity<AccountResponseDto> create(CreateAccountRequestDto createAccountRequestDto) throws BaseException {
		Account account = accountConverter.toAccount(createAccountRequestDto);
		accountService.create(account);
		return new ResponseEntity<>(accountConverter.toAccountResponseDto(account), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<AccountResponseDto> get(Long id) throws BaseException {
		Account account = accountService.get(id);
		return ResponseEntity.ok(accountConverter.toAccountResponseDto(account));
	}

	@Override
	public ResponseEntity<AccountResponseDto> update(CreateAccountRequestDto createAccountRequestDto) {
		Account account = accountConverter.toAccount(createAccountRequestDto);
		accountService.update(account);
		return ResponseEntity.ok(accountConverter.toAccountResponseDto(account));
	}

	@Override
	public ResponseEntity<?> delete(Long id) throws BaseException {
		accountService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<BigDecimal> getBalance(Long accountId) throws BaseException{
		Account account = accountService.get(accountId);
		return ResponseEntity.ok(account.getAccountBalance());
	}
	
	@Override
	public ResponseEntity<AccountResponseDto> findByAccountNumber(String accountNumber) throws BaseException {
		Account account = accountService.findByAccountNumber(accountNumber);
		return ResponseEntity.ok(accountConverter.toAccountResponseDto(account));
	}

	@Override
	public ResponseEntity<AccountResponseDto> findByIban(String iban) throws BaseException {
		Account account = accountService.findByIban(iban);
		return ResponseEntity.ok(accountConverter.toAccountResponseDto(account));
	}

	@Override
	public ResponseEntity<List<AccountResponseDto>> getAll() {
		return ResponseEntity.ok(toAccountResponseDtoList(accountService.getAll()));
	}

	public List<AccountResponseDto> toAccountResponseDtoList(List<Account> accounts) {
		List<AccountResponseDto> accountResponseDtos = new ArrayList<AccountResponseDto>();
		accounts.forEach(account -> {
			AccountResponseDto accountResponseDto = accountConverter.toAccountResponseDto(account);
			accountResponseDtos.add(accountResponseDto);
		});
		return accountResponseDtos;
	}

	@Override
	public ResponseEntity<List<AccountResponseDto>> findByAccountStatus(AccountStatus accountStatus) {
		return ResponseEntity.ok(toAccountResponseDtoList(accountService.findByAccountStatus(accountStatus)));
	}
	
	@Override
	public ResponseEntity<List<AccountResponseDto>> findByCustomer_Id(Long customerId) throws BaseException {
		return ResponseEntity.ok(toAccountResponseDtoList(accountService.findByCustomer_Id(customerId)));
	}

	@Override
	public ResponseEntity<List<AccountResponseDto>> findByCurrency_Id(Long currencyId) throws BaseException {
		return ResponseEntity.ok(toAccountResponseDtoList(accountService.findByCurrency_Id(currencyId)));
	}

	@Override
	public ResponseEntity<List<AccountResponseDto>> findByAccountType(AccountType accountType) {
		return ResponseEntity.ok(toAccountResponseDtoList(accountService.findByAccountType(accountType)));
	}

	@Override
	public ResponseEntity<List<AccountResponseDto>> findByBankCode(String bankCode) {
		return ResponseEntity.ok(toAccountResponseDtoList(accountService.findByBankCode(bankCode)));
	}

	@Override
	public ResponseEntity<List<AccountResponseDto>> findByBranchCode(String branchCode) {
		return ResponseEntity.ok(toAccountResponseDtoList(accountService.findByBranchCode(branchCode)));
	}

	@Override
	public ResponseEntity<List<AccountResponseDto>> findByBranchName(String branchName) {
		return ResponseEntity.ok(toAccountResponseDtoList(accountService.findByBranchName(branchName)));
	}

	@Override
	public ResponseEntity<List<AccountResponseDto>> findByBranchCodeAndCustomerId(String branchCode, Long customerId) {
		return ResponseEntity.ok(toAccountResponseDtoList(accountService.findByBranchCodeAndCustomerId(branchCode, customerId)));
	}

	@Override
	public ResponseEntity<List<AccountResponseDto>> findByAccountTypeAndCustomerId(AccountType accountType,
			Long customerId) {
		return ResponseEntity.ok(toAccountResponseDtoList(accountService.findByAccountTypeAndCustomerId(accountType, customerId)));
	}

	
	
	
}
