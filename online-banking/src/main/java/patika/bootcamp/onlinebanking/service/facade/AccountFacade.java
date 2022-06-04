package patika.bootcamp.onlinebanking.service.facade;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;

import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;

public interface AccountFacade {
	
	ResponseEntity<AccountResponseDto> create(CreateAccountRequestDto accountRequestDto) throws BaseException;
	ResponseEntity<AccountResponseDto> get(Long id) throws BaseException;
	ResponseEntity<AccountResponseDto> update(Account account);
	ResponseEntity<?> delete(Long id) throws BaseException;
	ResponseEntity<BigDecimal> getBalance(Long accountId) throws BaseException;
	
	ResponseEntity<AccountResponseDto> findByAccountNumber(String accountNumber) throws BaseException;
	ResponseEntity<AccountResponseDto> findByIban(String iban) throws BaseException;

	ResponseEntity<List<AccountResponseDto>> getAll();
	ResponseEntity<List<AccountResponseDto>> findByCustomer_Id(Long customerId) throws BaseException;
	ResponseEntity<List<AccountResponseDto>> findByCurrency_Id(Long currencyId) throws BaseException;
	ResponseEntity<List<AccountResponseDto>> findByAccountType(AccountType accountType);
	ResponseEntity<List<AccountResponseDto>> findByBankCode(String bankCode);
	ResponseEntity<List<AccountResponseDto>> findByBranchCode(String branchCode);
	ResponseEntity<List<AccountResponseDto>> findByBranchName(String branchName);
	ResponseEntity<List<AccountResponseDto>> findByBranchCodeAndCustomerId(String branchCode, Long customerId);
	ResponseEntity<List<AccountResponseDto>> findByAccountTypeAndCustomerId(AccountType accountType, Long customerId);
	ResponseEntity<List<AccountResponseDto>> findByAccountStatus(AccountStatus accountStatus);
}
