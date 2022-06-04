package patika.bootcamp.onlinebanking.service.facade.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.converter.transaction.TransactionConverter;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionToAccountRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.CreateTransactionToCardRequestDto;
import patika.bootcamp.onlinebanking.dto.transaction.TransactionResponseDto;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.ModeOfPayment;
import patika.bootcamp.onlinebanking.model.transaction.Transaction;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.BankCardService;
import patika.bootcamp.onlinebanking.service.TransactionService;
import patika.bootcamp.onlinebanking.service.facade.TransactionFacade;

@Service
@RequiredArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade{
	private final AccountService accountService;
	private final BankCardService bankCardService;
	private final TransactionService transactionService;
	private final TransactionConverter transactionConverter;

	@Override
	public ResponseEntity<TransactionResponseDto> monenyTransaction(CreateTransactionRequestDto createTransactionRequestDto) throws IOException {
		Transaction transaction = transactionConverter.toTransaction(createTransactionRequestDto);
		Account account = accountService.findByIban(createTransactionRequestDto.getSenderIbanNo());
		String customerNumber = account.getCustomer().getCustomerNumber();
		transaction.setSenderCustomerNumber(customerNumber);
		transaction = transactionService.monenyTransaction(transaction);
		
		return new ResponseEntity<>(transactionConverter.toTransactionResponseDto(transaction), HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<TransactionResponseDto> moneyTransactionToAccount(CreateTransactionToAccountRequestDto transactionToAccountRequestDto) throws IOException {
		Account from = accountService.findByAccountNumber(transactionToAccountRequestDto.getFromAccountNumber());
		Account to = accountService.findByAccountNumber(transactionToAccountRequestDto.getToAccountNumber());
		
		Transaction transaction = transactionConverter.toTransaction(transactionToAccountRequestDto, from, to);
		transaction = transactionService.monenyTransaction(transaction);
		
		return ResponseEntity.ok(transactionConverter.toTransactionResponseDto(transaction));
	}
	
	@Override
	public ResponseEntity<TransactionResponseDto> moneyTransactionToCard(CreateTransactionToCardRequestDto transactionToCardRequestDto) throws IOException {
		Account from = accountService.findByAccountNumber(transactionToCardRequestDto.getFromAccountNumber());
		Account to = (bankCardService.findByCardNumber(transactionToCardRequestDto.getToCardNumber())).getAccount();
		
		Transaction transaction = transactionConverter.toTransaction(transactionToCardRequestDto, from, to);
		transaction = transactionService.monenyTransaction(transaction);
		
		return ResponseEntity.ok(transactionConverter.toTransactionResponseDto(transaction));
	}

	@Override
	public ResponseEntity<List<TransactionResponseDto>> findByTransactionDate(Date transacionDate) {
		List<Transaction> transactions = transactionService.findByTransactionDate(transacionDate);
		return ResponseEntity.ok(toTransactionResponseDtoList(transactions));
	}

	public List<TransactionResponseDto> toTransactionResponseDtoList(List<Transaction> transactions) {
		List<TransactionResponseDto> transactionResponseDtos = new ArrayList<TransactionResponseDto>();
		transactions.forEach(transaction -> {
			TransactionResponseDto transactionResponseDto = transactionConverter.toTransactionResponseDto(transaction);
			transactionResponseDtos.add(transactionResponseDto);
		});
		return transactionResponseDtos;
	}

	@Override
	public ResponseEntity<List<TransactionResponseDto>> findByModeOfPayment(ModeOfPayment modeOfPayment) {
		List<Transaction> transactions = transactionService.findByModeOfPayment(modeOfPayment);
		return ResponseEntity.ok(toTransactionResponseDtoList(transactions));
	}

	@Override
	public ResponseEntity<List<TransactionResponseDto>> findBySenderIbanNo(String ibanNo) {
		List<Transaction> transactions = transactionService.findBySenderIbanNo(ibanNo);
		return ResponseEntity.ok(toTransactionResponseDtoList(transactions));
	}

	@Override
	public ResponseEntity<List<TransactionResponseDto>> findByRecipientIbanNo(String ibanNo) {
		List<Transaction> transactions = transactionService.findByRecipientIbanNo(ibanNo);
		return ResponseEntity.ok(toTransactionResponseDtoList(transactions));
	}

	@Override
	public ResponseEntity<List<TransactionResponseDto>> findBySenderAccountId(Long id) {
		List<Transaction> transactions = transactionService.findBySenderAccountId(id);
		return ResponseEntity.ok(toTransactionResponseDtoList(transactions));
	}
}
