package springeventstest.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;

import springeventstest.listener.EventListener;

public abstract class AbstractEventHandler implements ApplicationContextAware, ApplicationEventPublisher {

	protected ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Override
	public void publishEvent(ApplicationEvent event) {
		applicationContext.publishEvent(event);
	}
	
	public void subscribeEvent(EventListener<? extends ApplicationEvent> listener) {
		((ConfigurableApplicationContext) applicationContext).addApplicationListener(listener);
	}
}
