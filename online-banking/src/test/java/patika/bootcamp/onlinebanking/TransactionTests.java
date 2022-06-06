package patika.bootcamp.onlinebanking;

import java.io.IOException;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import patika.bootcamp.onlinebanking.controller.account.AccountController;
import patika.bootcamp.onlinebanking.controller.transaction.TransactionController;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.TransactionResponseDto;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.customer.Customer;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.BranchService;
import patika.bootcamp.onlinebanking.service.CurrencyService;
import patika.bootcamp.onlinebanking.service.CustomerService;
import patika.bootcamp.onlinebanking.util.generate.AccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.generate.AdditionalAccountNumberGenerator;
import patika.bootcamp.onlinebanking.util.generate.CustomerNumberGenerator;
import patika.bootcamp.onlinebanking.util.generate.IbanGenerator;

@SpringBootTest
public class TransactionTests {
	@Autowired
	TransactionController transactionController;

	@Autowired
	AccountController accountController;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	CurrencyService currencyService;
	
	@Autowired
	BranchService branchService;
	
	@Autowired
	CustomerService customerService;
	
	@Value("${bank.code}")
	private String bankCode;

	@Test
	void should_create_success_transaction_in_different_currencies() throws IOException {

		AccountResponseDto from = accountController.get(40L).getBody();// USD vadesiz hesap --> bakiye 80
		AccountResponseDto to = accountController.get(36L).getBody();// TRY vadesiz hesap              480.19

		Assertions.assertThat(from.getId()).isNotNull();
		Assertions.assertThat(from.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		Assertions.assertThat(from.getCurrencyId()).isEqualTo(4L);// USD id --> 4

		Assertions.assertThat(to.getId()).isNotNull();
		Assertions.assertThat(to.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		Assertions.assertThat(to.getCurrencyId()).isEqualTo(1L);// TRY id --> 4

		CreateTransactionRequestDto createTransactionRequestDto = new CreateTransactionRequestDto();
		createTransactionRequestDto.setAmount(BigDecimal.valueOf(1));
		createTransactionRequestDto.setModeOfPayment(ModeOfPayment.KonutKirasi);
		createTransactionRequestDto.setSenderCurrencyId(4L);
		createTransactionRequestDto.setSenderIbanNo(from.getIban());
		createTransactionRequestDto.setRecipientIbanNo(to.getIban());
		createTransactionRequestDto.setUseAllBalance(false);
		TransactionResponseDto transactionResponseDto = transactionController
				.monenyTransaction(createTransactionRequestDto).getBody();
		
		Assertions.assertThat(transactionResponseDto.getSenderIbanNo()).isEqualTo(from.getIban());
		Assertions.assertThat(transactionResponseDto.getRecipientIbanNo()).isEqualTo(to.getIban());
		Assertions.assertThat(transactionResponseDto.getAmount()).isEqualByComparingTo(new BigDecimal(1));
		
		Account fromAccount = accountService.findByIban(createTransactionRequestDto.getSenderIbanNo());
		Account toAccount = accountService.findByIban(createTransactionRequestDto.getRecipientIbanNo());
		
		Assertions.assertThat(fromAccount.getAccountBalance()).isEqualByComparingTo(new BigDecimal(79));
		Assertions.assertThat(toAccount.getAccountBalance()).isGreaterThan(to.getAccountBalance());
		Assertions.assertThat(fromAccount.getLockedBalance()).isEqualByComparingTo(new BigDecimal(0));
	}
	
	@Test
	void should_not_create_success_transaction_from_savingsaccount() throws IOException {

		AccountResponseDto from = accountController.get(38L).getBody();// TRY birikim hesabi
		AccountResponseDto to = accountController.get(30L).getBody();// TRY vadesiz hesap              

		Assertions.assertThat(from.getId()).isNotNull();
		Assertions.assertThat(from.getAccountType()).isEqualTo(AccountType.SAVINGS_ACCOUNT);
		Assertions.assertThat(from.getCurrencyId()).isEqualTo(4L);// USD id --> 4

		Assertions.assertThat(to.getId()).isNotNull();
		Assertions.assertThat(to.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		Assertions.assertThat(to.getCurrencyId()).isEqualTo(1L);// TRY id --> 4

		CreateTransactionRequestDto createTransactionRequestDto = new CreateTransactionRequestDto();
		createTransactionRequestDto.setAmount(BigDecimal.valueOf(1));
		createTransactionRequestDto.setModeOfPayment(ModeOfPayment.KonutKirasi);
		createTransactionRequestDto.setSenderCurrencyId(4L);
		createTransactionRequestDto.setSenderIbanNo(from.getIban());
		createTransactionRequestDto.setRecipientIbanNo(to.getIban());
		createTransactionRequestDto.setUseAllBalance(false);
		transactionController.monenyTransaction(createTransactionRequestDto).getBody();
	}

	@Test
	void should_not_create_success_transaction_in_insufficientbalance() throws IOException {

		AccountResponseDto from = accountController.get(40L).getBody();// TRY vadesiz hesap, anlik bakiye --> 80
		AccountResponseDto to = accountController.get(36L).getBody();// TRY vadesiz hesap              

		Assertions.assertThat(from.getId()).isNotNull();
		Assertions.assertThat(from.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		Assertions.assertThat(from.getCurrencyId()).isEqualTo(4L);// USD id --> 4

		Assertions.assertThat(to.getId()).isNotNull();
		Assertions.assertThat(to.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		Assertions.assertThat(to.getCurrencyId()).isEqualTo(1L);// TRY id --> 4

		CreateTransactionRequestDto createTransactionRequestDto = new CreateTransactionRequestDto();
		createTransactionRequestDto.setAmount(BigDecimal.valueOf(100));
		createTransactionRequestDto.setModeOfPayment(ModeOfPayment.KonutKirasi);
		createTransactionRequestDto.setSenderCurrencyId(4L);
		createTransactionRequestDto.setSenderIbanNo(from.getIban());
		createTransactionRequestDto.setRecipientIbanNo(to.getIban());
		createTransactionRequestDto.setUseAllBalance(false);
		transactionController.monenyTransaction(createTransactionRequestDto).getBody();
	}
	
	@Test
	void should_create_success_transaction() throws IOException {

		Account from = new Account();
		from.setAccountBalance(BigDecimal.valueOf(100));
		
		Branch branchFrom = new Branch();
		branchFrom.setBranchCode("250");
		branchFrom.setBranchName("Atakum Şubesi");
		branchFrom.addAccount(from);
		from.setBranch(branchFrom);

		from.setCurrency(currencyService.findByCode("TRY"));
		from.setAccountType(AccountType.CHECKING_ACCOUNT);
		
		Customer customerFrom = new Customer();
		customerFrom.setConfirmedByAdmin(true);
		String customerNumber = CustomerNumberGenerator.generate();
		customerFrom.addAccount(from);
		customerFrom.setCustomerNumber(customerNumber);
		from.setCustomer(customerFrom);
		
		String additionalAccountNumber1 = AdditionalAccountNumberGenerator.generate();
		String accountNumber1 = AccountNumberGenerator.generate(branchFrom.getBranchCode(), customerNumber, additionalAccountNumber1);
		from.setAccountNumber(accountNumber1);
		from.setIban(IbanGenerator.generate(bankCode, accountNumber1));



		Account to = new Account();
		to.setAccountBalance(BigDecimal.valueOf(100));
	
		Branch branchTo = new Branch();
		branchTo.setBranchCode("250");
		branchTo.setBranchName("Atakum Şubesi");
		//branchTo.setAccounts(Set.of(to));
		branchTo.addAccount(to);
		to.setBranch(branchTo);
	
		to.setCurrency(currencyService.findByCode("TRY"));
		to.setAccountType(AccountType.CHECKING_ACCOUNT);
		to.setCurrency(currencyService.findByCode("TRY"));
		
		Customer customerTo = new Customer();
		String customerNumber2 = CustomerNumberGenerator.generate();
		customerFrom.setConfirmedByAdmin(true);
		//customerTo.setAccounts(Set.of(to));
		customerTo.setCustomerNumber(customerNumber2);
		customerTo.addAccount(to);
		to.setCustomer(customerTo);
		
		
		String additionalAccountNumber2 = AdditionalAccountNumberGenerator.generate();
		String accountNumber2 = AccountNumberGenerator.generate(branchTo.getBranchCode(), customerNumber2, additionalAccountNumber2);
		to.setAccountNumber(accountNumber2);
		to.setIban(IbanGenerator.generate(bankCode, accountNumber2));
		
		accountService.create(from);
		accountService.create(to);

		Assertions.assertThat(from.getId()).isNotNull();
		Assertions.assertThat(from.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		Assertions.assertThat(from.getCurrency().getCode()).isEqualTo("TRY");
		Assertions.assertThat(from.getAccountBalance()).isEqualByComparingTo(new BigDecimal(100));

		Assertions.assertThat(to.getId()).isNotNull();
		Assertions.assertThat(to.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		Assertions.assertThat(to.getCurrency().getCode()).isEqualTo("TRY");
		Assertions.assertThat(to.getAccountBalance()).isEqualByComparingTo(new BigDecimal(100));
		
		Assertions.assertThat(branchFrom.getId()).isNotNull();
		Assertions.assertThat(branchFrom.getBranchCode()).isEqualTo("250");
		
		Assertions.assertThat(branchTo.getId()).isNotNull();
		Assertions.assertThat(branchTo.getBranchCode()).isEqualTo("250");
		
		Assertions.assertThat(customerFrom.getId()).isNotNull();
		Assertions.assertThat(customerFrom.getCustomerNumber()).isEqualTo(customerNumber);
		
		Assertions.assertThat(customerTo.getId()).isNotNull();
		Assertions.assertThat(customerTo.getCustomerNumber()).isEqualTo(customerNumber2);

		CreateTransactionRequestDto createTransactionRequestDto = new CreateTransactionRequestDto();
		createTransactionRequestDto.setAmount(BigDecimal.valueOf(20));
		createTransactionRequestDto.setModeOfPayment(ModeOfPayment.KonutKirasi);
		createTransactionRequestDto.setSenderCurrencyId(currencyService.findByCode("TRY").getId());//id 1 --> TRY
		createTransactionRequestDto.setSenderIbanNo(from.getIban());
		createTransactionRequestDto.setRecipientIbanNo(to.getIban());
		createTransactionRequestDto.setUseAllBalance(false);
		TransactionResponseDto transactionResponseDto = transactionController
				.monenyTransaction(createTransactionRequestDto).getBody();
		
		from = accountService.get(from.getId());
		to = accountService.get(to.getId());

		Assertions.assertThat(transactionResponseDto.getSenderIbanNo()).isEqualTo(from.getIban());
		Assertions.assertThat(transactionResponseDto.getRecipientIbanNo()).isEqualTo(to.getIban());
		Assertions.assertThat(transactionResponseDto.getAmount()).isEqualTo(BigDecimal.valueOf(20));
		
		//100 tl bakiye vardi, 20 tl gonderdi, 80 tl kalmasi lazim
		System.out.println("to bakiye: "+ to.getAccountBalance() + " -- from bakiye: " + from.getAccountBalance() + " -- from locked balance: " + from.getLockedBalance());
		Assertions.assertThat(to.getAccountBalance()).isEqualByComparingTo(new BigDecimal(120));
		Assertions.assertThat(from.getAccountBalance()).isEqualByComparingTo(new BigDecimal(80));
		Assertions.assertThat(from.getLockedBalance()).isEqualByComparingTo(new BigDecimal(0));
		
	}
}
