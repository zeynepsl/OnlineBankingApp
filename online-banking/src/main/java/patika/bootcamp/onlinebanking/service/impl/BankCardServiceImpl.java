package patika.bootcamp.onlinebanking.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BankCardServiceOperationException;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.card.BankCard;
import patika.bootcamp.onlinebanking.repository.card.BankCardRepository;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.BankCardService;
import patika.bootcamp.onlinebanking.validator.PasswordValidator;

@Service
@RequiredArgsConstructor
@Transactional
public class BankCardServiceImpl implements BankCardService{
	private final BankCardRepository bankCardRepository;
	private final AccountService accountService;
	private final PasswordValidator passwordValidator;
	
	@Override
	public BankCard create(BankCard bankCard) throws BaseException {
		bankCard = bankCardRepository.save(bankCard);
		return bankCard;
	}

	@Override
	public BankCard get(Long id) throws BaseException {
		BankCard bankCard = bankCardRepository.findById(id)
				.orElseThrow(() -> new BankCardServiceOperationException.BankCardNotFound("bank card not found"));
		return bankCard;
	}

	@Override
	public BankCard update(BankCard bankCard) {
		bankCard = bankCardRepository.save(bankCard);
		return bankCard;
	}

	@Override
	public void delete(Long id) throws BaseException {
		BankCard bankCard = get(id);
		bankCard = bankCard.removeAccount();
		bankCard = bankCard.removeCustomer();
		bankCardRepository.delete(bankCard);
	}

	@Override
	public List<BankCard> getAll() {
		return bankCardRepository.findAll();
	}

	@Override
	public BankCard findByCustomerId(Long customerId) {
		BankCard bankCard = bankCardRepository.findByCustomer_Id(customerId)
				.orElseThrow(() -> new BankCardServiceOperationException.BankCardNotFound("bank card not found"));
		return bankCard;
	}

	@Override
	public BankCard findByAccountId(Long accountId) {
		BankCard bankCard = bankCardRepository.findByAccount_Id(accountId)
				.orElseThrow(() -> new BankCardServiceOperationException.BankCardNotFound("bank card not found"));
		return bankCard;
	}

	@Override
	public BigDecimal getAccountBalance(Long bankCardId) {
		BankCard bankCard = get(bankCardId);
		return bankCard.getAccount().getAccountBalance();
	}

	@Override
	public void withdraw(BankCard bankCard, String password, BigDecimal amount) {
		passwordValidator.validate(password, bankCard.getPassword());
		
		Account account = bankCard.getAccount();
		BigDecimal accountBalance = account.getAccountBalance();
		
		validateBalance(amount, accountBalance);
		
		account.setAccountBalance(accountBalance.subtract(amount));
		accountService.update(account);
	}


	@Override
	public void deposit(BankCard bankCard, String password, BigDecimal amount) {
		passwordValidator.validate(password, bankCard.getPassword());
		Account account = bankCard.getAccount(); 
		account.setAccountBalance(account.getAccountBalance().add(amount));
		accountService.update(account);
	}

	@Override
	public String getIban(BankCard bankCard, String password) throws BaseException{
		passwordValidator.validate(password, bankCard.getPassword());
		return bankCard.getAccount().getIban();
	}
	
	public void validateBalance(BigDecimal amount, BigDecimal accountBalance) {
		if( (amount.compareTo(accountBalance) > 0) ) {
			throw new BankCardServiceOperationException.InsufficientBalance("InsufficientBalance");
		}
	}
	
	@Override
	public BankCard findByCardNumber(String cardNumber) {
		BankCard bankCard = bankCardRepository.findByCardNumber(cardNumber)
				.orElseThrow(() -> new BankCardServiceOperationException.BankCardNotFound("not found"));
		return bankCard;
	}
}
