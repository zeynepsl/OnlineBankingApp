package patika.bootcamp.onlinebanking.converter;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.dto.account.CurrencyResponseDto;
import patika.bootcamp.onlinebanking.dto.bank.BranchResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.util.generate.AccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.generate.AdditionalAccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.generate.IbanGenerator;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountConverter {
	
	public Account toAccount(CreateAccountRequestDto createAccountRequestDto) {
		Account account = new Account();
	
		account.setAccountType(createAccountRequestDto.getAccountType());
		account.setBankCode(createAccountRequestDto.getBankCode());
		account.setAccountType(createAccountRequestDto.getAccountType());
		
		account.setCreatedAt(new Date());
		account.setCreatedBy("Zeynep salman");
		account.setUpdatedAt(new Date());
		account.setUpdatedBy("Zeynep salman");
		return account;
	}

	public AccountResponseDto toAccountResponseDto(Account account) {
		AccountResponseDto accountResponseDto = new AccountResponseDto();
		accountResponseDto.setId(account.getId());
		accountResponseDto.setAccountBalance(account.getAccountBalance());
		accountResponseDto.setAccountNumber(account.getAccountNumber());
		accountResponseDto.setAccountStatus(account.getAccountStatus());
		accountResponseDto.setBankCode(account.getBankCode());
		
		
		accountResponseDto.setBranchId(account.getBranch().getId());
		
		accountResponseDto.setCurrencyId(account.getCurrency().getId());
		
		accountResponseDto.setCustomerId(account.getCustomer().getId());
		
		accountResponseDto.setIban(account.getIban());
		accountResponseDto.setBlockedAt(account.getBlockedAt());
		accountResponseDto.setCanBeActiveAt(account.getCanBeActiveAt());
		return accountResponseDto;
	}
}
