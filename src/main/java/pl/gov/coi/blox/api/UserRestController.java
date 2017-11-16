package pl.gov.coi.blox.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    public void add(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id")Long id){
        userService.deleteUser(id);
    }
    @GetMapping("/delete/all")
    public void deleteAllUsers(){
        userService.deleteAllUsers();
    }

    @GetMapping("/{id}")
    public UserViewDto get(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/")
    public List<UserViewDto> getAllUsers(){
        return userService.getAllUsers();
    }
}
