package patika.bootcamp.onlinebanking;

import java.io.IOException;
import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import patika.bootcamp.onlinebanking.controller.AccountController;
import patika.bootcamp.onlinebanking.controller.TransactionController;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.TransactionResponseDto;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;

@SpringBootTest
public class TransactionTests {
	@Autowired
	TransactionController transactionController;
	
	@Autowired
	AccountController accountController;
	
	@Test
	void should_create_success_transaction_in_different_currencies() throws IOException {
		
		AccountResponseDto from = accountController.get(40L).getBody();//USD vadesiz hesap --> bakiye 100
		AccountResponseDto to = accountController.get(36L).getBody();//TRY vadesiz hesap
	
		Assertions.assertThat(from.getId()).isNotNull();	
		Assertions.assertThat(from.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		Assertions.assertThat(from.getCurrencyId()).isEqualTo(4L);//USD id --> 4
		
		Assertions.assertThat(to.getId()).isNotNull();	
		Assertions.assertThat(to.getAccountType()).isEqualTo(AccountType.CHECKING_ACCOUNT);
		Assertions.assertThat(to.getCurrencyId()).isEqualTo(1L);//TRY id --> 4
		
		CreateTransactionRequestDto createTransactionRequestDto = new CreateTransactionRequestDto();
		createTransactionRequestDto.setAmount(BigDecimal.valueOf(1));
		createTransactionRequestDto.setModeOfPayment(ModeOfPayment.KonutKirasi);
		createTransactionRequestDto.setSenderCurrencyId(4L);
		createTransactionRequestDto.setSenderIbanNo(from.getIban());
		createTransactionRequestDto.setRecipientIbanNo(to.getIban());
		createTransactionRequestDto.setUseAllBalance(false);
		TransactionResponseDto transactionResponseDto = transactionController.monenyTransaction(createTransactionRequestDto).getBody();
		
		Assertions.assertThat(transactionResponseDto.getSenderIbanNo()).isEqualTo(from.getIban());
		Assertions.assertThat(transactionResponseDto.getRecipientIbanNo()).isEqualTo(to.getIban());
		Assertions.assertThat(transactionResponseDto.getAmount()).isEqualTo(BigDecimal.valueOf(1));
	}
}
