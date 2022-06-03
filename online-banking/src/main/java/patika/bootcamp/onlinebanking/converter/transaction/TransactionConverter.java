package patika.bootcamp.onlinebanking.converter.transaction;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.AccountConverter;
import patika.bootcamp.onlinebanking.converter.CurrencyConverter;
import patika.bootcamp.onlinebanking.converter.CustomerConverter;
import patika.bootcamp.onlinebanking.dto.account.AccountResponseDto;
import patika.bootcamp.onlinebanking.dto.account.CurrencyResponseDto;
import patika.bootcamp.onlinebanking.dto.customer.CustomerResponseDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionToAccountRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionToCardRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.TransactionResponseDto;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.account.Currency;
import patika.bootcamp.onlinebanking.model.transaction.Transaction;
import patika.bootcamp.onlinebanking.service.AccountService;

@Component
@RequiredArgsConstructor
public class TransactionConverter {
	
	private final AccountConverter accountConverter;
	private final CurrencyConverter currencyConverter;
	
	public Transaction toTransaction(CreateTransactionRequestDto createTransactionRequestDto) {
		Transaction transaction = new Transaction();
		transaction.setAmount(createTransactionRequestDto.getAmount());
		transaction.setModeOfPayment(createTransactionRequestDto.getModeOfPayment());
		transaction.setRecipientIbanNo(createTransactionRequestDto.getRecipientIbanNo());
		
		Currency currency = new Currency();
		currency.setId(createTransactionRequestDto.getSenderCurrencyId());
		transaction.setSenderCurrency(currency);
		
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
		
		CurrencyResponseDto currencyResponseDto = currencyConverter.toCurrencyResponseDto(transaction.getSenderCurrency());
		transactionResponseDto.setSenderCurrency(currencyResponseDto);
		
		AccountResponseDto accountResponseDto = accountConverter.toAccountResponseDto(transaction.getSenderAccount());
		transactionResponseDto.setSenderAccount(accountResponseDto);
		
		transactionResponseDto.setSenderIbanNo(transaction.getSenderIbanNo());
		transactionResponseDto.setTransactionDate(transaction.getTransactionDate());
		transactionResponseDto.setUseAllBalance(transaction.getUseAllBalance());
		
		return transactionResponseDto;
	}

	public Transaction toTransaction(CreateTransactionToAccountRequestDto transactionToAccountRequestDto, Account from, Account to) {
		Transaction transaction = new Transaction();
		transaction.setSenderIbanNo(from.getIban());
		transaction.setSenderCurrency(from.getCurrency());
		transaction.setSenderAccount(from);
		
		transaction.setAmount(transactionToAccountRequestDto.getAmount());
		transaction.setModeOfPayment(transactionToAccountRequestDto.getModeOfPayment());
		transaction.setRecipientIbanNo(to.getIban());
		return transaction;
	}

	public Transaction toTransaction(CreateTransactionToCardRequestDto transactionToCardRequestDto, Account from, Account to) {
		Transaction transaction = new Transaction();
		transaction.setSenderIbanNo(from.getIban());
		transaction.setSenderCurrency(from.getCurrency());
		transaction.setSenderAccount(from);
		
		transaction.setAmount(transactionToCardRequestDto.getAmount());
		transaction.setModeOfPayment(transactionToCardRequestDto.getModeOfPayment());
		transaction.setRecipientIbanNo(to.getIban());
		return transaction;
	}

}
