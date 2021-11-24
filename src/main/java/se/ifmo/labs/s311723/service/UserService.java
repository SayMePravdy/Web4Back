package se.ifmo.labs.s311723.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.ifmo.labs.s311723.entity.User;
import se.ifmo.labs.s311723.exception.FailedRegisterUserException;
import se.ifmo.labs.s311723.repository.UserRepository;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) throws FailedRegisterUserException {
        if (user.getLogin().length() > 30) {
            throw new FailedRegisterUserException("Username is too long. Please choose shorter");
        }

        if (user.getLogin().length() == 0) {
            throw new FailedRegisterUserException("Please choose not an empty username");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userByLogin = userRepository.findByLogin(user.getLogin());
        if (userByLogin == null) {
            userRepository.save(user);
        } else {
            throw new FailedRegisterUserException("Username is already in use");
        }

    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean checkUser(User user) {
        User userByLogin = userRepository.findByLogin(user.getLogin());
        if (userByLogin == null) {
            return false;
        }
        return passwordEncoder.matches(user.getPassword(), userByLogin.getPassword());
    }

}
