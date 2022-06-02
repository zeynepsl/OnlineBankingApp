package patika.bootcamp.onlinebanking.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.service.facade.AccountFacade;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
	private final AccountFacade accountFacade;
	
	@PostMapping("/")
	public ResponseEntity<?> create(@RequestBody CreateAccountRequestDto accountRequestDto){
		log.info("id {}", accountRequestDto.getBranchId());
		return accountFacade.create(accountRequestDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountResponseDto> get(@PathVariable Long id){
		return accountFacade.get(id);
	}
	
	@PutMapping("/")
	public ResponseEntity<AccountResponseDto> update(@RequestBody CreateAccountRequestDto createAccountRequestDto){
		return accountFacade.update(createAccountRequestDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return accountFacade.delete(id);
	}
	
	@GetMapping("/balance/{accountId}")
	public ResponseEntity<BigDecimal> getBalance(@PathVariable Long accountId){
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
		return accountFacade.findByCustomer_Id(customerId);
	}
	
	@GetMapping("/currency/{currencyId}")
	public ResponseEntity<List<AccountResponseDto>> findByCurrencyId(Long currencyId){
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
		return accountFacade.findByBranchCodeAndCustomerId(branchCode, customerId);
	}
	
	@GetMapping("/accountType/{accountType}/customer/{customerId}")
	public ResponseEntity<List<AccountResponseDto>> findByAccountTypeAndCustomerId(AccountType accountType, Long customerId){
		return accountFacade.findByAccountTypeAndCustomerId(accountType, customerId);
	}
}
