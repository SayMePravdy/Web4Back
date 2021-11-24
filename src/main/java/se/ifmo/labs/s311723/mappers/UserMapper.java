package se.ifmo.labs.s311723.mappers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.ifmo.labs.s311723.dto.UserDTO;
import se.ifmo.labs.s311723.entity.User;
import se.ifmo.labs.s311723.enums.Roles;
import se.ifmo.labs.s311723.repository.RoleRepository;

@Component
public class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO userToDto(User user) {
        return new UserDTO(user.getLogin(), user.getPassword());
    }

    public User DtoToUser(UserDTO userDTO) {
        return new User(userDTO.getLogin(), userDTO.getPassword(),
                roleRepository.findByName(Roles.USER.getName()));
    }

}
