package patika.bootcamp.onlinebanking.service.otoPayment;

import java.util.Date;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RunnableTask implements Runnable{
	
	@Override
	public void run() {
		log.info(new Date()+" Runnable Task with on thread "+Thread.currentThread().getName());
		//kredi ödeme
	}
	
}
