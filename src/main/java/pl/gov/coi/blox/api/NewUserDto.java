package pl.gov.coi.blox.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserDto {

    private int ID;
    private String login;
    private String password;
    private String email;
    private String dateOfRejestration;

}
