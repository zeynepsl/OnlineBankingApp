package patika.bootcamp.onlinebanking.converter;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.dto.account.CurrencyResponseDto;
import patika.bootcamp.onlinebanking.dto.bank.BranchResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.util.AccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.AdditionalAccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.IbanGenerator;

@Component
@RequiredArgsConstructor
public class AccountConverter {
	
	private final BranchConverter bankBranchConverter;
	private final CurrencyConverter currencyConverter;
	private final CustomerConverter customerConverter;
	
	public Account toAccount(CreateAccountRequestDto createAccountRequestDto) {
		Account account = new Account();
		
		String additionalAccountNumber = AdditionalAccountNumberGenerator.generate();
		account.setAdditionalAccountNumber(additionalAccountNumber);
		
		Customer customer = new Customer();
		customer.setId(createAccountRequestDto.getCustomerId());
		account.setCustomer(customer);
		
		Branch bankBranch = new Branch();
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

	public AccountResponseDto toAccountResponseDto(Account account) {
		AccountResponseDto accountResponseDto = new AccountResponseDto();
		accountResponseDto.setAccountBalance(account.getAccountBalance());
		accountResponseDto.setAccountNumber(account.getAccountNumber());
		accountResponseDto.setAccountStatus(account.getAccountStatus());
		accountResponseDto.setBankCode(account.getBankCode());
		
		BranchResponseDto bankBranchResponseDto = bankBranchConverter.toBankBranchResponseDto(account.getBankBranch());
		accountResponseDto.setBankBranchResponseDto(bankBranchResponseDto);
		
		CurrencyResponseDto currencyResponseDto = currencyConverter.toCurrencyResponseDto(account.getCurrency());
		accountResponseDto.setCurrencyResponseDto(currencyResponseDto);
		
		CustomerResponseDto customerResponseDto = CustomerConverter.toCustomerResponseDto(account.getCustomer());
		accountResponseDto.setCustomerResponseDto(customerResponseDto);
		
		accountResponseDto.setIban(account.getIban());
		accountResponseDto.setBlockedAt(account.getBlockedAt());
		accountResponseDto.setCanBeActiveAt(account.getCanBeActiveAt());
		return accountResponseDto;
	}
}
