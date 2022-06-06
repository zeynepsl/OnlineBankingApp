package patika.bootcamp.onlinebanking.controller.account;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.service.facade.AccountFacade;
import patika.bootcamp.onlinebanking.validator.Validator;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
	private final AccountFacade accountFacade;
	private final Validator<Long> idValidator;
	
	@PostMapping("/")
	public ResponseEntity<AccountResponseDto> create(@RequestBody CreateAccountRequestDto accountRequestDto){
		log.info("id {}", accountRequestDto.getBranchId());
		return accountFacade.create(accountRequestDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountResponseDto> get(@PathVariable Long id){
		idValidator.validate(id);
		return accountFacade.get(id);
	}
	
	@PutMapping("/")
	public ResponseEntity<AccountResponseDto> update(@RequestBody Account account){
		return accountFacade.update(account);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		idValidator.validate(id);
		return accountFacade.delete(id);
	}
	
	@GetMapping("/balance/{accountId}")
	public ResponseEntity<BigDecimal> getBalance(@PathVariable Long accountId){
		idValidator.validate(accountId);
		return accountFacade.getBalance(accountId);
	}
	
	@GetMapping("/accountNumber/{accountNumber}")
	public ResponseEntity<AccountResponseDto> findByAccountNumber(@PathVariable String accountNumber){
		return accountFacade.findByAccountNumber(accountNumber);
	}
	
	@GetMapping("/status/{accountStatus}")
	public ResponseEntity<List<AccountResponseDto>> findByAccountStatus(AccountStatus accountStatus) {
		return accountFacade.findByAccountStatus(accountStatus);
	}
	
	@GetMapping("/iban/{iban}")
	public ResponseEntity<AccountResponseDto> findByIban(@PathVariable String iban){
		return accountFacade.findByIban(iban);
	}

	@GetMapping("/")
	public ResponseEntity<List<AccountResponseDto>> getAll(){
		return accountFacade.getAll();
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<AccountResponseDto>> findByCustomerId(Long customerId){
		idValidator.validate(customerId);
		return accountFacade.findByCustomer_Id(customerId);
	}
	
	@GetMapping("/currency/{currencyId}")
	public ResponseEntity<List<AccountResponseDto>> findByCurrencyId(Long currencyId){
		idValidator.validate(currencyId);
		return accountFacade.findByCurrency_Id(currencyId);
	}
	
	@GetMapping("/accountType/{accountType}")
	public ResponseEntity<List<AccountResponseDto>> findByAccountType(AccountType accountType){
		return accountFacade.findByAccountType(accountType);
	}
	
	@GetMapping("/bankCode/{bankCode}")
	public ResponseEntity<List<AccountResponseDto>> findByBankCode(String bankCode){
		return accountFacade.findByBankCode(bankCode);
	}
	
	@GetMapping("/branchCode/{branchCode}")
	public ResponseEntity<List<AccountResponseDto>> findByBranchCode(String branchCode){
		return accountFacade.findByBranchCode(branchCode);
	}
	
	@GetMapping("/branchName/{branchName}")
	public ResponseEntity<List<AccountResponseDto>> findByBranchName(String branchName){
		return accountFacade.findByBranchName(branchName);
	}
	
	@GetMapping("/branchName/{branchName}/customer/{customerId}")
	public ResponseEntity<List<AccountResponseDto>> findByBranchCodeAndCustomerId(String branchCode, Long customerId){
		idValidator.validate(customerId);
		return accountFacade.findByBranchCodeAndCustomerId(branchCode, customerId);
	}
	
	@GetMapping("/accountType/{accountType}/customer/{customerId}")
	public ResponseEntity<List<AccountResponseDto>> findByAccountTypeAndCustomerId(AccountType accountType, Long customerId){
		idValidator.validate(customerId);
		return accountFacade.findByAccountTypeAndCustomerId(accountType, customerId);
	}
}
