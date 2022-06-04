package patika.bootcamp.onlinebanking.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import patika.bootcamp.onlinebanking.service.CreditCardService;

@EnableAsync
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool( creditCardService.findCardsThatHaveDebt().size() );
	}
}
