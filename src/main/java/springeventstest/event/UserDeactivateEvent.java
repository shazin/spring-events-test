package springeventstest.event;

import org.springframework.context.ApplicationEvent;


public class UserDeactivateEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5574871595880966192L;
	
	public UserDeactivateEvent(Object source) {
		super(source);
	}
	
	

}
