package patika.bootcamp.onlinebanking.converter.transaction;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.account.AccountConverter;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionToAccountRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionToCardRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.TransactionResponseDto;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.transaction.Transaction;

@Component
@RequiredArgsConstructor
public class TransactionConverter {
	
	private final AccountConverter accountConverter;
	
	public Transaction toTransaction(CreateTransactionRequestDto createTransactionRequestDto) {
		Transaction transaction = new Transaction();
		transaction.setAmount(createTransactionRequestDto.getAmount());
		transaction.setModeOfPayment(createTransactionRequestDto.getModeOfPayment());
		transaction.setRecipientIbanNo(createTransactionRequestDto.getRecipientIbanNo());
		
		transaction.setSenderIbanNo(createTransactionRequestDto.getSenderIbanNo());
		transaction.setUseAllBalance(createTransactionRequestDto.getUseAllBalance());
		
		return transaction;
	}

	public TransactionResponseDto toTransactionResponseDto(Transaction transaction) {
		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setId(transaction.getId());
		transactionResponseDto.setAmount(transaction.getAmount());
		transactionResponseDto.setModeOfPayment(transaction.getModeOfPayment());
		transactionResponseDto.setRecipientIbanNo(transaction.getRecipientIbanNo());
		
		AccountResponseDto senderAccountResponse = accountConverter.toAccountResponseDto(transaction.getSenderAccount());
		transactionResponseDto.setSenderAccount(senderAccountResponse);
		
		transactionResponseDto.setSenderIbanNo(transaction.getSenderIbanNo());
		transactionResponseDto.setTransactionDate(transaction.getTransactionDate());
		transactionResponseDto.setUseAllBalance(transaction.getUseAllBalance());
		
		return transactionResponseDto;
	}

	public Transaction toTransaction(CreateTransactionToAccountRequestDto transactionToAccountRequestDto, Account from, Account to) {
		Transaction transaction = new Transaction();
		transaction.setSenderIbanNo(from.getIban());
		//transaction.setSenderCurrency(from.getCurrency());
		transaction.setSenderAccount(from);
		
		transaction.setAmount(transactionToAccountRequestDto.getAmount());
		transaction.setModeOfPayment(transactionToAccountRequestDto.getModeOfPayment());
		transaction.setRecipientIbanNo(to.getIban());
		return transaction;
	}

	public Transaction toTransaction(CreateTransactionToCardRequestDto transactionToCardRequestDto, Account from, Account to) {
		Transaction transaction = new Transaction();
		transaction.setSenderIbanNo(from.getIban());
		//transaction.setSenderCurrency(from.getCurrency());
		transaction.setSenderAccount(from);
		transaction.setRecipientIbanNo(to.getIban());
		
		transaction.setAmount(transactionToCardRequestDto.getAmount());
		transaction.setModeOfPayment(transactionToCardRequestDto.getModeOfPayment());
		return transaction;
	}

}
