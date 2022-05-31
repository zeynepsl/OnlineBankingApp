package patika.bootcamp.onlinebanking.service.otoPaymentOld;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.model.card.CreditCard;
import patika.bootcamp.onlinebanking.service.CreditCardService;

@Component
@RequiredArgsConstructor
@EnableAsync
public class Payment {
	private final ThreadPoolTaskScheduler taskScheduler;
	
	
	private final CreditCardService creditCardService;
	
	CronTrigger cronTrigger = new CronTrigger("0 10 10 15 * ?");//her ayın 10 unda saat 10 15 te yürütülecek görevi planlıyoruz

	
	@Async
	public void runPaymentTask() {
		List<ExecutorService> executorServices = new ArrayList<>() ;
		List<CreditCard> cardsHaveDebt = creditCardService.findCardsThatHaveDebt();
		for( CreditCard card : cardsHaveDebt )
		{
		    ExecutorService es = Executors.newSingleThreadExecutor() ;
		    executorServices.add( es ) ;
		    //es.submit(  );
		}
		taskScheduler.schedule(new RunnableTask(), cronTrigger);
	}
}
