package springeventstest.event;

import org.springframework.context.ApplicationEvent;


public class AuthenticationFailedEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8147040402813515766L;
	
	public AuthenticationFailedEvent(Object source) {
		super(source);
	}

}
