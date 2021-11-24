package se.ifmo.labs.s311723.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.ifmo.labs.s311723.config.jwt.JwtProvider;
import se.ifmo.labs.s311723.dto.UserDTO;
import se.ifmo.labs.s311723.entity.User;
import se.ifmo.labs.s311723.exception.FailedRegisterUserException;
import se.ifmo.labs.s311723.mappers.UserMapper;
import se.ifmo.labs.s311723.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/register",
            produces = "application/json")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        User user = userMapper.DtoToUser(userDTO);
        try {
            userService.saveUser(user);
            return ResponseEntity.ok().body("User added");
        } catch (FailedRegisterUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody UserDTO userDTO) {
        User user = userMapper.DtoToUser(userDTO);
        if (userService.checkUser(user)) {
            String token = jwtProvider.generateToken(user.getLogin());
            return ResponseEntity.ok().body(token);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }
}
