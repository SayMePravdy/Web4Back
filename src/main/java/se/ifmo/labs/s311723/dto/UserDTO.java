package se.ifmo.labs.s311723.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserDTO {
    @NonNull
    private String login;

    @NonNull
    private String password;


}
