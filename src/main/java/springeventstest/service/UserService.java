package springeventstest.service;

import springeventstest.domain.User;

public interface UserService {

	User getUser(String name);
	
	User updateUser(User user);
	
	User deactivateUser(User user);
	
	default User exceptionThrow() {
		throw new IllegalStateException("User is not valid!");		
	};
	
	void deleteUser(Long id);
	
	void login(String username, String password);
	
	
}
