package springeventstest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import springeventstest.domain.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);
	
}
