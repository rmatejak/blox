package pl.gov.coi.blox.entity;

import lombok.Getter;
import lombok.Setter;
import pl.gov.coi.blox.model.RoleType;

@Getter
@Setter
public class UserDto {

    private String name;
    private String secondname;
    private String login;
    private String password;
    private String email;
    private RoleType roleType;
}
