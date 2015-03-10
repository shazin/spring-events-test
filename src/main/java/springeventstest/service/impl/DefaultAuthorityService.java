package springeventstest.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import springeventstest.event.UserDeactivateEvent;
import springeventstest.handler.EventHandler;
import springeventstest.listener.AuthenticationFailedEventListener;
import springeventstest.listener.EventListener;
import springeventstest.listener.UserDeactivateEventListener;
import springeventstest.service.AuthorityService;
import springeventstest.service.UserService;

@Service
@Transactional(readOnly = true)
public class DefaultAuthorityService implements
		AuthorityService {

	@Autowired
	private UserService userService;

	@Autowired
	private EventHandler eventHandler;

	private UserDeactivateEventListener userDeactivateEventListener = (
			obbEvent) -> {
		System.out.printf(
				"Transaction Synchronization Active %s in thread %s\n",
				TransactionSynchronizationManager.isSynchronizationActive(),
				Thread.currentThread().getName());
		System.out
				.printf("User Deactivate Event fired at %d lambda subscriber\n", System.currentTimeMillis());

		userService.exceptionThrow();

	};
	
	private AuthenticationFailedEventListener authenticationFailedEventListener = (event) -> {
		System.out.printf(
				"Transaction Synchronization Active %s in thread %s\n",
				TransactionSynchronizationManager.isSynchronizationActive(),
				Thread.currentThread().getName());
		System.out
				.printf("Authentication Failed Event fired at %d lambda subscriber\n", System.currentTimeMillis());
	};

	@PostConstruct
	public void init() {
		eventHandler.subscribeEvent(userDeactivateEventListener);
		eventHandler.subscribeEvent(authenticationFailedEventListener);
	}
}
