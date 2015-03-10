package springeventstest;

import java.util.Calendar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springeventstest.config.AppConfig;
import springeventstest.domain.User;
import springeventstest.service.UserService;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		final UserService userService = annotationConfigApplicationContext
				.getBean(UserService.class);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 365);
		User user = new User(1l, "Shazin", 28, cal.getTime(), true);

		User updatedUser = userService.updateUser(user);

		new Thread(() -> {
			try {
				userService.deactivateUser(updatedUser);
			} catch (Exception e) {
				System.out.println("Error occurred must have rolled back!");
			}
		}).start();

		user = userService.getUser("Shazin");

		System.out.print(String.format(
				"User status : %s and active until : %s\n", user.getStatus(),
				user.getActiveUntil()));
		
		userService.deleteUser(user.getId());
		
		userService.login("shazin", "password");

	}
}
