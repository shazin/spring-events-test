package springeventstest.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public interface EventListener<T extends ApplicationEvent> extends ApplicationListener<T> {

	public void onApplicationEvent(T event);

}
