package patika.bootcamp.onlinebanking.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.AccountServiceOperationException;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.repository.account.AccountRepository;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.CustomerService;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
	private final AccountRepository accountRepository;
	private final CustomerService customerService;

	//to do: throws BaseException
	@Override
	public Account create(Account account) throws BaseException{
		//eger olusturulan hesap vadesiz ise ve kullanıcı ilk defa vadesiz hesap oluşturuyorsa bir banka kartı oluştur
		account = accountRepository.save(account);
		return account;
	}

	@Override
	public Account get(Long id) throws BaseException{
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountServiceOperationException.AccountNotFound("Account not found"));
		return account;
	}

	@Override
	public Account update(Account account) {
		accountRepository.save(account);
		return account;
	}

	@Override
	public void delete(Long id) throws BaseException{
		Account account = get(id);
		BigDecimal accountBalance = account.getAccountBalance();
		if(accountBalance.compareTo(BigDecimal.valueOf(0)) > 0) {
			throw new AccountServiceOperationException.AccountCanNotDeleted("Account has balance so cannot deleted");
		}
		accountRepository.delete(account);
	}

	@Override
	public Account findByAccountNumber(String accountNumber) throws BaseException{
		Account account = accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountServiceOperationException.AccountNotFound("Account not found"));
		return account;
	}

	@Override
	public Account findByIban(String iban) throws BaseException{
		Account account = accountRepository.findByIban(iban)
				.orElseThrow(() -> new AccountServiceOperationException.AccountNotFound("Account not found"));
		return account;
	}

	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public List<Account> findByCustomer_Id(Long customerId) throws BaseException{
		customerService.get(customerId);//customer yoksa exception atar
		return accountRepository.findByCustomer_Id(customerId);
		
	}

	@Override
	public List<Account> findByCurrency_Id(Long currencyId) throws BaseException{
		//currencyService e bağlan
		return accountRepository.findByCurrency_Id(currencyId);
	}

	@Override
	public List<Account> findByAccountType(AccountType accountType) {
		return accountRepository.findByAccountType(accountType);
	}

	@Override
	public List<Account> findByBankCode(String bankCode) {
		return accountRepository.findByBankCode(bankCode);
	}

	@Override
	public List<Account> findByBranchCode(String branchCode) {
		return accountRepository.findByBankBranch_BranchCode(branchCode);
	}
	
	@Override
	public List<Account> findByBranchName(String branchName) {
		return accountRepository.findByBankBranch_BranchName(branchName);
	}
	
	@Override
	public List<Account> findByBranchNameAndCustomerId(String branchName, Long customerId) {
		return accountRepository.findByBankBranch_BranchNameAndCustomer_Id(branchName, customerId);
	}
	
}
