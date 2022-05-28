package patika.bootcamp.onlinebanking.service.otoPaymentOld;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Payment {
	private final ThreadPoolTaskScheduler taskScheduler;
	
	CronTrigger cronTrigger = new CronTrigger("0 10 10 15 * ?");//her ayın 10 unda saat 10 15 te yürütülecek görevi planlıyoruz

	
	public void runPaymentTask() {
		taskScheduler.schedule(new RunnableTask(), cronTrigger);
	}
}
