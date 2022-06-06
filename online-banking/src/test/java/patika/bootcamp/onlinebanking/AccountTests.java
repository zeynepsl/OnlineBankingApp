package patika.bootcamp.onlinebanking;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import patika.bootcamp.onlinebanking.controller.account.AccountController;
import patika.bootcamp.onlinebanking.controller.customer.CustomerController;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.model.enums.Gender;
import patika.bootcamp.onlinebanking.service.AccountService;

@SpringBootTest
public class AccountTests {

	@Autowired
	AccountController accountController;

	@Autowired
	AccountService accountService;

	@Autowired
	CustomerController customerController;

	@Value("${bank.code}")
	private String bankCode;

	//detached entity passes to persist
	
	
	@Test
	void should_create_success_bankcard_while_customer_s_first_checkingaccount() {
		CreateCustomerRequestDto createCustomerRequestDto = new CreateCustomerRequestDto();
		createCustomerRequestDto.setAge(45);
		createCustomerRequestDto.setEmail("asdasdasd@asd.com");
		createCustomerRequestDto.setGender(Gender.MALE);
		createCustomerRequestDto.setIdentityNumber(7893742374L);
		createCustomerRequestDto.setPhoneNumber("+901111111111");
		createCustomerRequestDto.setSecondaryPhoneNumber("+901111111111");
		createCustomerRequestDto.setPassword("678");
		CustomerResponseDto customerResponseDto = customerController.create(createCustomerRequestDto).getBody();
		
		Long customerId = customerResponseDto.getId();
		customerController.confirmCustomer(customerId);
		
		Assertions.assertThat(customerId).isNotNull();
		Assertions.assertThat(customerResponseDto.getEmail()).isEqualTo("asdasdasd@asd.com");
		

		CreateAccountRequestDto createAccountRequestDto = new CreateAccountRequestDto();
		createAccountRequestDto.setAccountType(AccountType.CHECKING_ACCOUNT);
		createAccountRequestDto.setBranchId(1L);
		createAccountRequestDto.setCurrencyId(1L);// TRY
		createAccountRequestDto.setCustomerId(customerId);
		AccountResponseDto accountResponseDto = accountController.create(createAccountRequestDto).getBody();

		Assertions.assertThat(accountResponseDto.getId()).isNotNull();
		Assertions.assertThat(accountResponseDto.getAccountNumber()).isNotNull();
		Assertions.assertThat(accountResponseDto.getAccountBalance()).isEqualByComparingTo(new BigDecimal(0));
		Assertions.assertThat(accountResponseDto.getBankCode()).isEqualTo(bankCode);
		Assertions.assertThat(accountResponseDto.getIban()).isNotNull();
		Assertions.assertThat(accountResponseDto.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		Assertions.assertThat(accountResponseDto.getAccountStatus()).isEqualTo(AccountStatus.PASSIVE);

		Account account = accountService.get(accountResponseDto.getId());
		BankCard bankCard = account.getBankCard();
		Assertions.assertThat(bankCard.getId()).isNotNull();
		Assertions.assertThat(bankCard.getCardNumber()).isNotNull();

	}
}
