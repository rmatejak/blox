package pl.gov.coi.blox.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String login;
    private String username;
    private String password;
    private String email;
}
