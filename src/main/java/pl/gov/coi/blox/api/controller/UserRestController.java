package pl.gov.coi.blox.api.controller;

import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.gov.coi.blox.api.UsersApi;
import pl.gov.coi.blox.api.model.BlogDto;
import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.api.model.UserDto;
import pl.gov.coi.blox.api.model.UserViewDto;
import pl.gov.coi.blox.service.UserService;

import javax.validation.Path;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserRestController implements UsersApi{

    private final UserService userService;


    @Override
    public ResponseEntity<Void> addBlogToUser(@Valid @PathVariable Long id, @Valid @RequestBody BlogDto blogDto) {
        userService.addBlogToUser(id, blogDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addCommentToUser(@Valid @PathVariable Long id,@Valid @RequestBody CommentDto userComment) {
        userService.addCommentToUser(id, userComment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addUser(@Valid @RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(@Valid @PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserViewDto>> getAllUsers() {
        return new ResponseEntity<List<UserViewDto>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserViewDto> getUserById(@Valid @PathVariable Long id) {
        return new ResponseEntity<UserViewDto>(userService.getUserById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateUser(@Valid @PathVariable Long id,@Valid @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
