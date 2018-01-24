package pl.gov.coi.blox.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gov.coi.blox.api.mapper.BlogMapper;
import pl.gov.coi.blox.api.mapper.BlogMapperImpl;
import pl.gov.coi.blox.api.mapper.CommentMapper;
import pl.gov.coi.blox.api.mapper.CommentMapperImpl;
import pl.gov.coi.blox.api.mapper.UserMapper;
import pl.gov.coi.blox.api.mapper.UserMapperImpl;
import pl.gov.coi.blox.api.model.BlogDto;
import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.api.model.UserDto;
import pl.gov.coi.blox.api.model.UserViewDto;
import pl.gov.coi.blox.entity.BlogEntity;
import pl.gov.coi.blox.entity.CommentEntity;
import pl.gov.coi.blox.entity.UserEntity;
import pl.gov.coi.blox.repository.BlogRepository;
import pl.gov.coi.blox.repository.CommentRepository;
import pl.gov.coi.blox.repository.UserRepository;
import pl.gov.coi.blox.validation.ValidationMessage;
import pl.gov.coi.blox.validation.ValidationPredicates;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final static UserMapper usermapper = new UserMapperImpl();
    private final static BlogMapper blogmapper = new BlogMapperImpl();
    private final static CommentMapper commentmapper = new CommentMapperImpl();

    public void addUser(UserDto userDto) {
        Preconditions.checkNotNull(userDto, "New User cannot be NULL");
        ValidationPredicates.lenght(2,30,userDto.getName(), ValidationMessage.NAME_SIZE_NOT_CORRECT);
        ValidationPredicates.lenght(2,30,userDto.getSecondName(), ValidationMessage.SECONDNAME_SIZE_NOT_CORRECT);
        ValidationPredicates.lenght(2,30,userDto.getLogin(), ValidationMessage.LOGIN_SIZE_NOT_CORRECT);
        ValidationPredicates.lenght(10,50,userDto.getEmail(), ValidationMessage.EMAIL_SIZE_NOT_CORRECT);
        ValidationPredicates.emailMatcher(userDto.getEmail(),ValidationMessage.EMAIL_MATCH_NOT_CORRECT);
        ValidationPredicates.passwordRequirementsMatcher(userDto.getPassword(),ValidationMessage.PASSWORD_REGEX);
        ValidationPredicates.passwordMatcher(userDto.getPassword(),userDto.getConfirmedPassword(),ValidationMessage.CONFIRMED_PASSWORD_IS_DIFFERENT);
        System.out.println("Dodanie usera: "+userDto);
        UserEntity userEntity = usermapper.map(userDto);
        userRepository.save(userEntity);
    }

    public void addBlogToUser(Long id,BlogDto blogDto) {
        Preconditions.checkNotNull(blogDto, "Blog cannot be NULL");
        Preconditions.checkNotNull(id,"Id cannot be NULL.");

        System.out.println("Dodanie bloga: "
                + blogDto.getBlogType() + " "
                + blogDto.getDescription() + " "
                + blogDto.getStatus());
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity == null) {
            throw new BusinessException("User not found");
        }
        BlogEntity blogEntity = blogmapper.map(blogDto);
        userEntity.addBlog(blogEntity);
        blogRepository.save(blogEntity);
        userRepository.save(userEntity);
    }

    @Override
    public void addCommentToUser(Long id, CommentDto commentDto) {
        Preconditions.checkNotNull(id,"Id cannot be NULL.");
        Preconditions.checkNotNull(commentDto, "CommentDto cannot be NULL.");

        CommentEntity commentEntity = commentmapper.map(commentDto);
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity == null) {
            throw new BusinessException("User not found");
        }
        userEntity.addComment(commentEntity);
        commentRepository.save(commentEntity);
        userRepository.save(userEntity);
    }

    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id);
        userRepository.delete(id);
    }

    public UserViewDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity == null){
            throw new BusinessException("User not found");
        }
        return usermapper.map(userEntity);
    }

    public List<UserViewDto> getAllUsers(){
        List<UserEntity> listofusers = userRepository.findAll();
        if (listofusers == null){
            throw new BusinessException("List is empty");
        }
        return usermapper.map(listofusers);
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        Preconditions.checkNotNull(id, "Id cannot be NULL!");
        ValidationPredicates.lenght(2,30,userDto.getName(), ValidationMessage.NAME_SIZE_NOT_CORRECT);
        ValidationPredicates.lenght(2,30,userDto.getSecondName(), ValidationMessage.SECONDNAME_SIZE_NOT_CORRECT);
        ValidationPredicates.lenght(2,30,userDto.getLogin(), ValidationMessage.LOGIN_SIZE_NOT_CORRECT);
        ValidationPredicates.lenght(10,50,userDto.getEmail(), ValidationMessage.EMAIL_SIZE_NOT_CORRECT);
        ValidationPredicates.emailMatcher(userDto.getEmail(),ValidationMessage.EMAIL_MATCH_NOT_CORRECT);
        ValidationPredicates.passwordRequirementsMatcher(userDto.getPassword(),ValidationMessage.PASSWORD_REGEX);
        ValidationPredicates.passwordMatcher(userDto.getPassword(),userDto.getConfirmedPassword(),ValidationMessage.CONFIRMED_PASSWORD_IS_DIFFERENT);
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity == null){
            throw new BusinessException("User not found");
        }
        userEntity = usermapper.map(userDto,userEntity);
        userRepository.save(userEntity);
        //Warto dokonczyc walidacje
    }
}
