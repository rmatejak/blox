package pl.gov.coi.blox.service;
import pl.gov.coi.blox.api.model.BlogDto;
import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.api.model.UserDto;
import pl.gov.coi.blox.api.model.UserViewDto;

import java.util.List;

public interface UserService {
    void addUser(UserDto userDto);
    void addBlogToUser(Long id, BlogDto blogDto);
    void addCommentToUser(Long id, CommentDto commentDto);
    void deleteUser(Long id);
    UserViewDto getUserById(Long id);
    List<UserViewDto> getAllUsers();
    void updateUser(Long id, UserDto userDto);
}
