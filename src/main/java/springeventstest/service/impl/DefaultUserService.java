package springeventstest.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springeventstest.domain.User;
import springeventstest.event.AuthenticationFailedEvent;
import springeventstest.event.UserDeactivateEvent;
import springeventstest.handler.EventHandler;
import springeventstest.repo.UserRepository;
import springeventstest.service.UserService;

@Service("userService")
@Transactional(readOnly=true)
public class DefaultUserService implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EventHandler userDeactiveEventHandler;
	
	@Autowired
	private EventHandler authenticationFailedHandler;

	public User getUser(String name) {
		return userRepo.findByName(name);
	}

	@Transactional(rollbackFor=Exception.class)
	public User updateUser(User user) {
		User updatedUser = userRepo.save(user);
		//userDeactiveEventPublisher.publishEvent(new ObbEvent(this, String.format("User %d is Updated", updatedUser.getId()), ObbEvent.EventType.USER_UPDATE));
		
		return updatedUser;
	}

	@Transactional(rollbackFor=Exception.class)
	public User deactivateUser(User user) {
		user.setStatus(Boolean.FALSE);
		user.setActiveUntil(new Date());
		
		User updatedUser = updateUser(user);
		userDeactiveEventHandler.publishEvent(new UserDeactivateEvent(this));
		
		return updatedUser;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteUser(Long id) {
		userRepo.delete(id);
		
		//userDeactiveEventPublisher.publishEvent(new ObbEvent(this, String.format("User %d is Deactivated", id), ObbEvent.EventType.USER_DELETE));
	}

	@Override
	public void login(String username, String password) {
		authenticationFailedHandler.publishEvent(new AuthenticationFailedEvent(this));
	}
	
	
	
	

}
