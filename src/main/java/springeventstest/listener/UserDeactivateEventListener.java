package springeventstest.listener;

import springeventstest.event.UserDeactivateEvent;

public interface UserDeactivateEventListener extends EventListener<UserDeactivateEvent> {
	
	public void onApplicationEvent(UserDeactivateEvent event);

}
