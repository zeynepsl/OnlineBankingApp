package patika.bootcamp.onlinebanking.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.model.card.CreditCard;

@Component
public class OtoDebtPayment {
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Autowired
	private AsyncTaskExecutor taskExecuter;
	
	@Async
	public void paymentDebt(CreditCard card) throws BaseException, IOException {
        System.out.println("A - " + Thread.currentThread().getName());
        //processingWorkers();
		creditCardService.basePaymentDebt(card, card.getAmountOfDebt());
	}

	@Scheduled(cron = "0 10 10 15 * ?")
	public void processingWorkers() {
		List<CreditCard> cards = creditCardService.findCardsThatHaveDebt();
		int numberOfCardsWithDebt = cards.size();
        for(int i = 0; i < numberOfCardsWithDebt; i++) {
        	//final Integer inner = new Integer(i); veya:
        	final int a = i;
        	taskExecuter.submit( () -> {
				try {
					paymentDebt(cards.get(a));
				} catch (BaseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
        }
	}
}
