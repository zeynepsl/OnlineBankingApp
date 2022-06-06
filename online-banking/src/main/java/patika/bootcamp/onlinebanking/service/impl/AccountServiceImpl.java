package patika.bootcamp.onlinebanking.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.AccountServiceOperationException;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.repository.account.AccountRepository;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.util.generate.CardNumberGenerator;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {
	private final AccountRepository accountRepository;

	@Override
	public Account create(Account account) throws BaseException {	
		account = accountRepository.save(account);
		return account;
	}

	@Override
	public BankCard createBankCardWhileCreatingFirstCheckingAccount(Account account) {
		BankCard bankCard = new BankCard();
		bankCard.setAccount(account);
		bankCard.setIsActive(true);
		bankCard.setCardNumber(
				CardNumberGenerator.generate(account.getBranch().getBranchCode(), account.getAccountNumber()));
		bankCard.setCreatedAt(new Date());
		bankCard.setCreatedBy("Zeynep Salman");
		bankCard.setCustomer(account.getCustomer());
		bankCard.setPassword(UUID.randomUUID().toString());
		bankCard.setUpdatedAt(new Date());
		bankCard.setUpdatedBy("Zeynep Salman");
		return bankCard;
	}

	@Override
	public Account get(Long id) throws BaseException {
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
	public void delete(Long id) throws BaseException {
		Account account = get(id);
		BigDecimal accountBalance = account.getAccountBalance();
		if (accountBalance.compareTo(BigDecimal.valueOf(0)) > 0) {
			throw new AccountServiceOperationException.AccountCanNotDeleted("Account has balance so cannot deleted");
		}
		account.removeCurrency(account.getCurrency());
		account.removeCustomer(account.getCustomer());
		account.removeBranch(account.getBranch());
		account.removeBankCard(account.getBankCard());
		accountRepository.delete(account);
	}
	
	@Override
	public BigDecimal getBalance(Long accountId) throws BaseException{
		Account account = get(accountId);
		return account.getAccountBalance();
	}

	@Override
	public Account findByAccountNumber(String accountNumber) throws BaseException {
		Account account = accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountServiceOperationException.AccountNotFound("Account not found"));
		return account;
	}

	@Override
	public Account findByIban(String iban) throws BaseException {
		Account account = accountRepository.findByIban(iban)
				.orElseThrow(() -> new AccountServiceOperationException.AccountNotFound("Account not found"));
		return account;
	}

	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public List<Account> findByCustomer_Id(Long customerId) throws BaseException {
		return accountRepository.findByCustomer_Id(customerId);

	}
	
	@Override
	public List<Account> findByAccountStatus(AccountStatus accountStatus) {
		return accountRepository.findByAccountStatus(accountStatus);
	}
	
	@Override
	public List<Account> findByCurrency_Id(Long currencyId){
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
		return accountRepository.findByBranch_BranchCode(branchCode);
	}

	@Override
	public List<Account> findByBranchName(String branchName) {
		return accountRepository.findByBranch_BranchName(branchName);
	}

	@Override
	public List<Account> findByBranchCodeAndCustomerId(String branchCode, Long customerId) {
		return accountRepository.findByBranch_BranchCodeAndCustomer_Id(branchCode, customerId);
	}

	@Override
	public List<Account> findByAccountTypeAndCustomerId(AccountType accountType, Long customerId) {
		return accountRepository.findByAccountTypeAndCustomer_Id(accountType, customerId);
	}

}
