package patika.bootcamp.onlinebanking.converter;

import java.util.Date;

import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.dto.request.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.model.bank.BankBranch;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.util.AccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.AdditionalAccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.IbanGenerator;

@Component
public class AccountConverter {
	Account toAccount(CreateAccountRequestDto createAccountRequestDto) {
		Account account = new Account();
		
		String additionalAccountNumber = AdditionalAccountNumberGenerator.generate();
		account.setAdditionalAccountNumber(additionalAccountNumber);
		
		Customer customer = new Customer();
		customer.setId(createAccountRequestDto.getCustomerId());
		account.setCustomer(customer);
		
		BankBranch bankBranch = new BankBranch();
		bankBranch.setId(createAccountRequestDto.getBankBranchId());
		String accountNumber = AccountNumberGenerator.generate(bankBranch.getBranchCode(), customer.getCustomerNumber(), additionalAccountNumber);
		account.setAccountNumber(accountNumber);
		
		account.setIban(IbanGenerator.generate(createAccountRequestDto.getBankCode(), accountNumber));
		account.setAccountType(createAccountRequestDto.getAccountType());
		account.setBankCode(createAccountRequestDto.getBankCode());
		
		account.setBankBranch(bankBranch);
		
		Currency currency = new Currency();
		currency.setId(createAccountRequestDto.getCurrencyId());
		account.setCurrency(currency);
		
		account.setCreatedAt(new Date());
		account.setCreatedBy("Zeynep salman");
		account.setUpdatedAt(new Date());
		account.setUpdatedBy("Zeynep salman");
		return account;
	}
}
