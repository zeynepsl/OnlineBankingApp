package patika.bootcamp.onlinebanking.service.otoPaymentOld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import patika.bootcamp.onlinebanking.service.CreditCardService;

@Configuration
public class ThreadPoolTaskSchedulerConfig {
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		
		/*bu projede eşzamanlı işlem sayisi, borcu olan kredi kart sayisi kadar ayarlandi
		 * fakat burasi kesinlikle farki bir sistemle yappilmali*/
		int threadCount = creditCardService.findCardsThatHaveDebt().size();
		threadPoolTaskScheduler.setPoolSize(threadCount);
		
		threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
		return threadPoolTaskScheduler;
	}
}
