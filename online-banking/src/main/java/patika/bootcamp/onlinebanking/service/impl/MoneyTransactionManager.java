package patika.bootcamp.onlinebanking.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.TransactionServiceOperationException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.model.transaction.Transaction;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.TransactionService;

@Service
@RequiredArgsConstructor
@Transactional
public class MoneyTransactionManager implements TransactionService {

	private final AccountService accountService;



	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void monenyTransaction(Transaction transaction) {
		Account from = accountService.findByIban(transaction.getSenderIbanNo());
		Account to = accountService.findByAccountNumber(transaction.getRecipientIbanNo());

		validateAccountType(from.getAccountType());

		BigDecimal fromBalance = from.getAccountBalance();
		BigDecimal toBalance = to.getAccountBalance();
		
		boolean useAllBalance = transaction.getUseAllBalance();
		if (useAllBalance) {
			deductFromAccount(from, fromBalance);
		} else {
			deductFromAccount(from, transaction.getAmount());
		}

		BigDecimal lockedBalance = from.getLockedBalance();
		
		

		validateBalance(fromBalance, lockedBalance);

		from.setAccountBalance(fromBalance.subtract(lockedBalance));
		to.setAccountBalance(toBalance.add(lockedBalance));
		from.setLockedBalance(BigDecimal.ZERO);

		accountService.update(to);
		accountService.update(from);
	}

	@Override
	public void validateAccountType(AccountType fromAccountType) {
		if (fromAccountType == AccountType.SAVINGS_ACCOUNT) {
			throw new TransactionServiceOperationException.UnSupportedAccountType(
					"Money transfer cannot be made from savings account.");
		}
	}

	@Override
	public void validateBalance(BigDecimal accountBalance, BigDecimal amount) throws BaseException {
		if (accountBalance.compareTo(amount) < 0) {
			throw new TransactionServiceOperationException.InsufficientBalance(
					"insufficient balance in your bank account");
		}
	}

	// isolation read commited
	@Override
	public void deductFromAccount(Account from, BigDecimal amount) {
		BigDecimal lockedBalance = from.getLockedBalance();
		lockedBalance.add(amount);
		from.setLockedBalance(lockedBalance);
		accountService.update(from);
	}

}
