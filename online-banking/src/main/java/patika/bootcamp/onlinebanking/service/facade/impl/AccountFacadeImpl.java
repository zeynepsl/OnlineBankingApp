package patika.bootcamp.onlinebanking.service.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.onlinebanking.converter.account.AccountConverter;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.exception.AccountServiceOperationException;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.BranchService;
import patika.bootcamp.onlinebanking.service.CustomerService;
import patika.bootcamp.onlinebanking.service.facade.AccountFacade;
import patika.bootcamp.onlinebanking.util.generate.AccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.generate.AdditionalAccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.generate.IbanGenerator;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AccountFacadeImpl implements AccountFacade{
	private final AccountService accountService;
	private final AccountConverter accountConverter;
	private final BranchService branchService;
	private final CustomerService customerService;
	
	@Value("${bank.code}")
	private String bankCode;
	
	@Override
	public ResponseEntity<AccountResponseDto> create(CreateAccountRequestDto createAccountRequestDto) throws BaseException {
		Customer customer = customerService.get(createAccountRequestDto.getCustomerId());
		if( !customer.isConfirmedByAdmin() ) {
			throw new AccountServiceOperationException.UnconfirmedCustomer("Unconfirmed customer cannot open account");
		}
		Account account = accountConverter.toAccount(createAccountRequestDto);
		
		String additionalAccountNumber = AdditionalAccountNumberGenerator.generate();
		account.setAdditionalAccountNumber(additionalAccountNumber);
		
		customer.addAccount(account);
		account.setCustomer(customer);
		
		/*Branch bankBranch = new Branch();
		bankBranch.setId(createAccountRequestDto.getBranchId());
		account.setBranch(bankBranch); bu kullanim mantikli fakat ise yaramiyor, branch i sadece id si ile getiriyor, 
		brach in diger alanlarini null olarak getiriyor
		fakat benim branch in diger alanlarina da ihityacim var su an*/
		 
		Branch branch = branchService.get(createAccountRequestDto.getBranchId());
		branch.addAccount(account);
		account.setBranch(branch);
		
		String accountNumber = AccountNumberGenerator.generate(branch.getBranchCode(), customer.getCustomerNumber(), additionalAccountNumber);
		account.setAccountNumber(accountNumber);
		
		account.setIban(IbanGenerator.generate(bankCode, accountNumber));
		
		Currency currency = new Currency();
		currency.setId(createAccountRequestDto.getCurrencyId());
		log.info("currency kodu: {}", currency.getCode());//mesela burasi null veriyor, yukaridaki gibi currency nin bir fieldına ihtiyacim olsaydi service den tüm nesneyi getirmem gerekecekti
		account.setCurrency(currency);
		
		AccountType accountType = createAccountRequestDto.getAccountType();
		if (accountType == AccountType.CHECKING_ACCOUNT) {
			log.info("customer {}",createAccountRequestDto.getCustomerId());
			int size = accountService.findByAccountTypeAndCustomerId(accountType, createAccountRequestDto.getCustomerId()).size();
			log.info("size {}",size);
			//isEmpty() ise yaramiyor, boyutu 1 olarak algiliyor ama liste bos :( , garip olan da test ederken size'ın sifir olarak algilanmasi
			//yani; app calisirken size 1 mi, app'i test ederken 0 mi diye kontrol :| 
			if ( size == 1 ) {
				log.info("kullanici ilk defa vadesiz hesap olusturuyor");
				BankCard bankCard = accountService.createBankCardWhileCreatingFirstCheckingAccount(account);
				log.info("bankkCard id {}", bankCard);
				account.setBankCard(bankCard);
				bankCard.setAccount(account);
			}
		}
		
		account = accountService.create(account);
		return new ResponseEntity<>(accountConverter.toAccountResponseDto(account), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<AccountResponseDto> get(Long id) throws BaseException {
		Account account = accountService.get(id);
		return ResponseEntity.ok(accountConverter.toAccountResponseDto(account));
	}

	@Override
	public ResponseEntity<AccountResponseDto> update(Account account) {
		account = accountService.update(account);
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
