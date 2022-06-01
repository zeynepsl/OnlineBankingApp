package patika.bootcamp.onlinebanking.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.dto.card.CreateOnlineTransferByCardRequestDto;
import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.CreditCardServiceOperationException;
import patika.bootcamp.onlinebanking.model.account.Account;
import patika.bootcamp.onlinebanking.model.bank.Branch;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.model.enums.AccountType;
import patika.bootcamp.onlinebanking.repository.card.CreditCardRepository;
import patika.bootcamp.onlinebanking.service.AccountService;
import patika.bootcamp.onlinebanking.service.CreditCardService;
import patika.bootcamp.onlinebanking.util.converter.CurrencyConverter;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditCardServiceImpl implements CreditCardService{
	private final CreditCardRepository creditCardRepository;
	private final AccountService accountService;
	private final CurrencyConverter currencyConverter;

	@Override
	public CreditCard create(CreditCard creditCard) throws BaseException {
		creditCard = creditCardRepository.save(creditCard);
		return creditCard;
	}

	@Override
	public CreditCard get(Long id) throws BaseException {
		CreditCard creditCard = creditCardRepository.findById(id)
				.orElseThrow(() -> new CreditCardServiceOperationException.CreditCardNotFound("Credit card not found"));
		return creditCard;
	}

	@Override
	public CreditCard update(CreditCard creditCard) {
		creditCard = creditCardRepository.save(creditCard);
		return creditCard;
	}

	@Override
	public void delete(Long id) throws BaseException {
		CreditCard creditCard = get(id);
		if(creditCard.getAmountOfDebt().compareTo(BigDecimal.ZERO) > 0) {
			throw new CreditCardServiceOperationException.CreditCardCannotDeleted("a card with debt cannot be deleted");
		}
		creditCardRepository.delete(creditCard);
	}

	@Override
	public List<CreditCard> getAll() {
		return creditCardRepository.findAll();
	}

	@Override
	public CreditCard findByCustomer_Id(Long customerId) {
		CreditCard creditCard = creditCardRepository.findByCustomer_Id(customerId)
				.orElseThrow(() -> new CreditCardServiceOperationException.CreditCardNotFound("Credit card not found"));
		return creditCard;
	}

	@Override
	public List<CreditCard> findByAccountCutOffDate(Date accountCutOffDate) {
		return creditCardRepository.findByAccountCutOffDate(accountCutOffDate);
	}

	@Override
	public BigDecimal getCardLimit(Long creditCardId) {
		CreditCard creditCard = get(creditCardId);
		return creditCard.getCardLimit();
	}

	@Override
	public BigDecimal getAvailableLimit(Long creditCardId) {
		CreditCard creditCard = get(creditCardId);
		return creditCard.getAvailableLimit();
	}

	@Override
	public BigDecimal getPeriodExpenditures(Long creditCardId) {
		CreditCard creditCard = get(creditCardId);
		return creditCard.getPeriodExpenditures();
	}

	@Override
	public BigDecimal getAmountOfDebt(Long creditCardId) {
		CreditCard creditCard = get(creditCardId);
		return creditCard.getAmountOfDebt();
	}

	public void paymentDebtFromAccount(CreditCard creditCard, BigDecimal amountOfDebt, Account account) {
		account.setAccountBalance(account.getAccountBalance().subtract(amountOfDebt));
		accountService.update(account);
		creditCard.setAmountOfDebt(BigDecimal.ZERO);
		update(creditCard);
	}

	@Override
	public void moneyTransfer(CreditCard creditCard, String password, String to, BigDecimal amount) throws BaseException{
		/*password ler uyuşmalı
		 * uyuşmuyorsa hata ver --> burada ileride bcryptEncoder devreye girecek
		 * 
		 * amount u periyodikHarcamalar ile topla, çıkan sonuç limiti aşıyorsa hata ver
		 * 
		 * aşmıyorsa devam et:
		 * creditCardı borç miktari ile amount u topla
		 * to nun hesabına amount u aktar
		 * 
		 * bitir
		 * */
		
		creditCardPasswordValidate(creditCard, password);
		updateAvailableLimitAndDebtInCreditCard(creditCard, amount);
		updateToAccount(to, amount);		
	}

	public void creditCardPasswordValidate(CreditCard creditCard, String password) throws BaseException{
		if( !(creditCard.getPassword()).equals(password) ) {
			throw new CreditCardServiceOperationException.PasswordWrong("The password you entered is incorrect");
		}
	}

	public void updateToAccount(String to, BigDecimal amount) {
		Account toAccount = accountService.findByAccountNumber(to);
		toAccount.setAccountBalance(toAccount.getAccountBalance().add(amount));
		accountService.update(toAccount);
	}

	public void validateCreditCardLimit(BigDecimal limit, BigDecimal newPeriodExpenditures) throws BaseException{
		if(newPeriodExpenditures.compareTo(limit) > 0) {
			throw new CreditCardServiceOperationException.InsufficientCreditCardLimit("you are exceeding the credit card limit");
		}
	}

	@Override
	public void onlineMoneyTransfer(CreateOnlineTransferByCardRequestDto onlineTransferByCardRequestDto){
		/*o card numarasına ait kartı getir
		 * o kartın firstname i, cvv si vs.. hepsi girilenlerle aynı değilse hata ver
		 * 
		 * aynıysa devam et
		 * */
		String cardNo = onlineTransferByCardRequestDto.getCardNo();
		String firstName = onlineTransferByCardRequestDto.getFirstName();
		String lastName = onlineTransferByCardRequestDto.getLastName();
		BigDecimal amount = onlineTransferByCardRequestDto.getAmount();
		String cvv = onlineTransferByCardRequestDto.getCvv();
		Date dueDate = onlineTransferByCardRequestDto.getDueDate();
		String to = onlineTransferByCardRequestDto.getTo();
		
		CreditCard creditCard = findByCardNumber(cardNo);
		
		validateOnlineTransferInfo(firstName, lastName, cvv, dueDate, creditCard);
		updateAvailableLimitAndDebtInCreditCard(creditCard, amount);
		updateToAccount(to, amount);
	}

	public void validateOnlineTransferInfo(String firstName, String lastName, String cvv, Date dueDate,
			CreditCard creditCard) {
		if( !(creditCard.getCustomer().getFirstName().equals(firstName) || !(creditCard.getCustomer().getLastName().equals(lastName)) 
				|| !(creditCard.getCvv().equals(cvv)) || (creditCard.getDueDate().compareTo(dueDate) != 0)) ) {
			throw new CreditCardServiceOperationException.WrongCardInformation("wrong card information");
		}
	}

	public void updateAvailableLimitAndDebtInCreditCard(CreditCard creditCard, BigDecimal amount) {
		
		BigDecimal limit = creditCard.getCardLimit();
		BigDecimal newPeriodExpenditures = creditCard.getPeriodExpenditures().add(amount);
		
		validateCreditCardLimit(limit, newPeriodExpenditures);
		
		creditCard.setAvailableLimit(limit.subtract(newPeriodExpenditures));
		creditCard.setAmountOfDebt(creditCard.getAmountOfDebt().add(amount));
		update(creditCard);
	}

	@Override
	public void paymentDebtFromCashMachine(CreditCard creditCard, String password) throws BaseException, IOException {
		creditCardPasswordValidate(creditCard, password);
		basePaymentDebt(creditCard);
	}

	public void basePaymentDebt(CreditCard creditCard) throws BaseException, IOException{
		Branch creditCardBankBranch = creditCard.getBankBranch();
		BigDecimal amountOfDebt = creditCard.getAmountOfDebt();
		//kredi kartı sahibinin kredi kart aldığı şubedeki hesaplari:
		List<Account> accounts = accountService.findByBranchCodeAndCustomerId(creditCardBankBranch.getBranchCode(), creditCard.getCustomer().getId());
		
		//o şubedeki hesapların arasından ilk once; vadesiz tl hesabına bakılır: vadesiz tl hesabı bakiyesi yeterliyse borç bu hesaptan ödenir: 
		accounts.forEach(account -> {
			if((account.getAccountType() == AccountType.CHECKING_ACCOUNT) && (account.getCurrency().getName() == "TRY")) {
				if(account.getAccountBalance().compareTo(amountOfDebt) > 0) {
					paymentDebtFromAccount(creditCard, amountOfDebt, account);
					return;
				}
			}
		});
		
		//vadesiz tl hesabında yeterli bakiye kalmamış, o zaman diğer para birimlerindeki hesaplara bak (forEach continue kabul etmedi :) )
		for (Account account : accounts) {
			if((account.getAccountType() == AccountType.CHECKING_ACCOUNT) && (account.getCurrency().getName() == "TRY")) {
				continue;
			}
			/*kredi kartı borcum tl cinsinden
			 *  o account un currencyTipini al
			 *     currencyconverter a gonder( accountCurrencyType dan --> TRY tipine
			 * */
			String fromCurrency = account.getCurrency().getCode();
			String toCurrency = "TRY";
			Double rate = currencyConverter.converter(toCurrency, fromCurrency);
			BigDecimal newAmountOfDebt = amountOfDebt.subtract(BigDecimal.valueOf(rate));
			paymentDebtFromAccount(creditCard, newAmountOfDebt, account);
			return;
		}
		throw new CreditCardServiceOperationException.InsufficientBalance("Your credit card debt is not paid. you have insufficient balance");
	}

	@Override
	public void paymentDebtFromAccount(Long accountId) {
		/*hesabı getir
		 * o hesabın customerının kredi kartını getir
		 * 
		 * o hesabın bakiyesi yeterliyse
		 *  hesabın bakiyesinden kredi karı borcunu öde
		 *  hesap bakiyesini güncelle
		 *  
		 * yeterli değilse hata ver
		 *  */
		
		Account account = accountService.get(accountId);
		BigDecimal accountBalance = account.getAccountBalance();
		
		CreditCard creditCard = account.getCustomer().getCreditCard();
		BigDecimal amountOfDebt = creditCard.getAmountOfDebt();
		
		validateBalance(accountBalance, amountOfDebt);
		
		account.setAccountBalance(accountBalance.subtract(amountOfDebt));
		accountService.update(account);
		
		creditCard.setAmountOfDebt(BigDecimal.ZERO);
		update(creditCard);
	}

	public void validateBalance(BigDecimal accountBalance, BigDecimal amountOfDebt) {
		if(accountBalance.compareTo(amountOfDebt) > 0) {
			throw new CreditCardServiceOperationException.InsufficientBalance("Your credit card debt is not paid. you have insufficient balance");
		}
	}
	
	@Override
	public CreditCard findByCardNumber(String cardNumber) {
		CreditCard creditCard = creditCardRepository.findByCardNumber(cardNumber)
				.orElseThrow(() -> new CreditCardServiceOperationException.CreditCardNotFound("credit card not found"));
		return creditCard;
	}
	
	@Override
	public List<CreditCard> findCardsThatHaveDebt() {
		List<CreditCard> allCreditCards = new ArrayList<CreditCard>();
		allCreditCards.addAll(getAll());
		
		List<CreditCard> cardsThatHaveDebt = allCreditCards
				.stream()
				.filter(c -> c.getAmountOfDebt().compareTo(BigDecimal.ZERO) > 0)
				.collect(Collectors.toList());
		
		return cardsThatHaveDebt;
	}
	
	
}
