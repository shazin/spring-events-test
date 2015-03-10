package springeventstest.listener;

import springeventstest.event.AuthenticationFailedEvent;

public interface AuthenticationFailedEventListener extends EventListener<AuthenticationFailedEvent> {

	public void onApplicationEvent(AuthenticationFailedEvent event);
	
}
