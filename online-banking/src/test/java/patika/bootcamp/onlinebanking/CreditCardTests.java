package patika.bootcamp.onlinebanking;

import java.io.IOException;
import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import patika.bootcamp.onlinebanking.controller.account.AccountController;
import patika.bootcamp.onlinebanking.controller.card.CreditCardController;
import patika.bootcamp.onlinebanking.controller.customer.CustomerController;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CreateAccountRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreateCreditCardRequestDto;
import patika.bootcamp.onlinebanking.dto.card.CreditCardResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CreateCustomerRequestDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.dto.transaction.TransactionWithCardResponseDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.model.enums.AccountStatus;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.model.enums.Gender;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.CreditCardService;

@SpringBootTest
public class CreditCardTests {

	@Autowired
	CreditCardController creditCardController;
	
	@Autowired
	CreditCardService creditCardService;
	
	@Autowired
	AccountController accountController;
	
	@Autowired
	CustomerController customerController;
	
	@Autowired
	AccountService accountService;
	
	
	
	@Value("${bank.code}")
	private String bankCode;
	
	
	@Test
	void should_create_success_money_transfer_with_creditCard() throws BaseException, IOException {
		CreateCustomerRequestDto createCustomerRequestDto = new CreateCustomerRequestDto();
		createCustomerRequestDto.setAge(46);
		createCustomerRequestDto.setEmail("asd@asd.com");
		createCustomerRequestDto.setGender(Gender.MALE);
		createCustomerRequestDto.setIdentityNumber(1323222L);
		createCustomerRequestDto.setPhoneNumber("+901111111111");
		createCustomerRequestDto.setSecondaryPhoneNumber("+901111111111");
		createCustomerRequestDto.setPassword("12345");
		CustomerResponseDto customerResponseDto = customerController.create(createCustomerRequestDto).getBody();
		Long customerId = customerResponseDto.getId();
		customerController.confirmCustomer(customerId);
		
		Assertions.assertThat(customerId).isNotNull();
		Assertions.assertThat(customerResponseDto.getEmail()).isEqualTo("asd@asd.com");	
		
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
		
		
		
		
		CreateCreditCardRequestDto creditCardRequestDto = new CreateCreditCardRequestDto();
		creditCardRequestDto.setBankBranchId(1L);
		creditCardRequestDto.setCardLimit(new BigDecimal(20_000));
		creditCardRequestDto.setCustomerId(customerId);
		creditCardRequestDto.setPassword("12345");
		CreditCardResponseDto creditCardResponseDto = creditCardController.create(creditCardRequestDto).getBody();
		
		Assertions.assertThat(creditCardResponseDto.getAmountOfDebt()).isEqualByComparingTo(new BigDecimal(0));
		Assertions.assertThat(creditCardResponseDto.getAvailableLimit()).isEqualByComparingTo(new BigDecimal(20_000));
		Assertions.assertThat(creditCardResponseDto.getCardNumber()).isNotNull();
		Assertions.assertThat(creditCardResponseDto.getPeriodExpenditures()).isEqualByComparingTo(new BigDecimal(0));
		
		
		CreateAccountRequestDto toAccount = new CreateAccountRequestDto();
		toAccount.setAccountType(AccountType.CHECKING_ACCOUNT);
		toAccount.setBranchId(1L);
		toAccount.setCurrencyId(1L);// TRY
		toAccount.setCustomerId(72L);
		AccountResponseDto toAccountResponse = accountController.create(toAccount).getBody(); 

		Long toAccountId = toAccountResponse.getId();
		
		Assertions.assertThat(toAccountId).isNotNull();
		Assertions.assertThat(toAccountResponse.getAccountNumber()).isNotNull();
		Assertions.assertThat(toAccountResponse.getAccountBalance()).isEqualByComparingTo(new BigDecimal(0));
		Assertions.assertThat(toAccountResponse.getBankCode()).isEqualTo(bankCode);
		Assertions.assertThat(toAccountResponse.getIban()).isNotNull();
		Assertions.assertThat(toAccountResponse.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		
		
		CreditCard fromCreditCard = creditCardService.get(creditCardResponseDto.getId());
		Account to = accountService.get(toAccountId);
		
		TransactionWithCardResponseDto transactionwithCardResponseDto = creditCardController.moneyTransfer(fromCreditCard, "12345", to.getAccountNumber(), new BigDecimal(150)).getBody();
		
		to = accountService.get(toAccountId);
		Assertions.assertThat(to.getAccountBalance()).isEqualByComparingTo(new BigDecimal(150));//bakiye sifirdi, 150 olmasi lazim
		Assertions.assertThat(transactionwithCardResponseDto.getSenderCustomerNUmber()).isEqualTo(customerResponseDto.getCustomerNumber());
		Assertions.assertThat(transactionwithCardResponseDto.getRecipientIbanNo()).isEqualTo(to.getIban());
		Assertions.assertThat(transactionwithCardResponseDto.getAmount()).isEqualByComparingTo(new BigDecimal(150));
		
	}
}
