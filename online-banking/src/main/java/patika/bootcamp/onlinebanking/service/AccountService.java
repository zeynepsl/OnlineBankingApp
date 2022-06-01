package patika.bootcamp.onlinebanking.service;

import java.math.BigDecimal;
import java.util.List;

import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;

public interface AccountService extends BaseService<Account>{
	
	Account findByAccountNumber(String accountNumber) throws BaseException;
	Account findByIban(String iban) throws BaseException;
	BigDecimal getBalance(Long accountId);

	List<Account> findByCustomer_Id(Long customerId) throws BaseException;
	List<Account> findByCurrency_Id(Long currencyId);
	List<Account> findByAccountType(AccountType accountType);
	List<Account> findByAccountStatus(AccountStatus accountStatus);
	List<Account> findByBankCode(String bankCode);
	List<Account> findByBranchCode(String branchCode);
	List<Account> findByBranchName(String branchName);
	List<Account> findByBranchCodeAndCustomerId(String branchCode, Long customerId);
	List<Account> findByAccountTypeAndCustomerId(AccountType accountType, Long customerId);
	BankCard createBankCardWhileCreatingFirstCheckingAccount(Account account);
}
