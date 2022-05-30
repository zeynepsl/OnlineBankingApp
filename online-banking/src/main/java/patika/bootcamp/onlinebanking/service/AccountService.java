package patika.bootcamp.onlinebanking.service;

import java.util.List;

import patika.bootcamp.onlinebanking.core.service.BaseService;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountType;

public interface AccountService extends BaseService<Account>{
	
	Account findByAccountNumber(String accountNumber) throws BaseException;
	Account findByIban(String iban) throws BaseException;

	List<Account> findByCustomer_Id(Long customerId) throws BaseException;
	List<Account> findByCurrency_Id(Long currencyId) throws BaseException;
	List<Account> findByAccountType(AccountType accountType);
	List<Account> findByBankCode(String bankCode);
	List<Account> findByBranchCode(String branchCode);
	List<Account> findByBranchName(String branchName);
	List<Account> findByBranchNameAndCustomerId(String branchName, Long customerId);
}
