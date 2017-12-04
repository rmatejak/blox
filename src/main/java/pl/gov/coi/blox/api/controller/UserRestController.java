package pl.gov.coi.blox.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gov.coi.blox.entity.UserDto;
import pl.gov.coi.blox.entity.UserViewDto;
import pl.gov.coi.blox.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/")
    public void addUser(@RequestBody UserDto userDto) { userService.addUser(userDto); }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) { userService.deleteUser(id); }
    @DeleteMapping("/delete/all")
    public void deleteAllUsers() { userService.deleteAllUsers(); }
    @GetMapping("/{id}")
    public UserViewDto getUser(@PathVariable("id") Long id) { return userService.getUserById(id); }
    @GetMapping("/")
    public List<UserViewDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
