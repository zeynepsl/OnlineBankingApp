package patika.bootcamp.onlinebanking.converter.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.model.account.Account;

@Component
public class AccountConverter {
	
	@Value("${bank.code}")
	private String bankCode;
	
	public Account toAccount(CreateAccountRequestDto createAccountRequestDto) {
		Account account = new Account();
	
		account.setAccountType(createAccountRequestDto.getAccountType());
		account.setBankCode(bankCode);
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
		accountResponseDto.setAccountType(account.getAccountType());
		
		accountResponseDto.setBranchId(account.getBranch().getId());
		
		accountResponseDto.setCurrencyId(account.getCurrency().getId());
		
		accountResponseDto.setCustomerId(account.getCustomer().getId());
		
		accountResponseDto.setIban(account.getIban());
		accountResponseDto.setBlockedAt(account.getBlockedAt());
		accountResponseDto.setCanBeActiveAt(account.getCanBeActiveAt());
		return accountResponseDto;
	}
}
