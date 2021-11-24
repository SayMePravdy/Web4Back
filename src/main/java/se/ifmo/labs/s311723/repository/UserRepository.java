package se.ifmo.labs.s311723.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ifmo.labs.s311723.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String username);

//    User findByLoginAndPassword(String login, String password);

}
