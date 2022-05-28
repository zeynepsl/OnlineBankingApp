package patika.bootcamp.onlinebanking.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OtoPaymentDebt {
	
	@Scheduled(cron = "0 10 10 15 * ?")
	public void paymentDebt() {
		//oto kredi borcu ödeme
	}
}
