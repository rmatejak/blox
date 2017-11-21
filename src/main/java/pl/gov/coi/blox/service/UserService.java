package pl.gov.coi.blox.service;

import pl.gov.coi.blox.entity.UserDto;
import pl.gov.coi.blox.entity.UserViewDto;
import java.util.List;

public interface UserService {
    void addUser(UserDto userForm);
    void deleteUser(Long id);
    void deleteAllUsers();
    UserViewDto getUserById(Long id);
    List<UserViewDto> getAllUsers();
}
