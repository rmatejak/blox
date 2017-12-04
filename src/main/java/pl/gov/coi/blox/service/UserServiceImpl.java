package pl.gov.coi.blox.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.blox.api.mapper.UserMapper;
import pl.gov.coi.blox.api.mapper.UserMapperImpl;
import pl.gov.coi.blox.entity.UserDto;
import pl.gov.coi.blox.entity.UserViewDto;
import pl.gov.coi.blox.model.UserEntity;
import pl.gov.coi.blox.repository.UserRepository;
import pl.gov.coi.blox.validation.ValidationMessage;
import pl.gov.coi.blox.validation.ValidationPredicates;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final static UserMapper usermapper = new UserMapperImpl();

    public void addUser(UserDto userDto) {
        Preconditions.checkNotNull(userDto, "New User cannot be NULL");
        ValidationPredicates.lenght(2,30,userDto.getName(), ValidationMessage.NAME_SIZE_NOT_CORRECT);
        ValidationPredicates.lenght(2,30,userDto.getSecondname(), ValidationMessage.SECONDNAME_SIZE_NOT_CORRECT);
        ValidationPredicates.lenght(2,30,userDto.getLogin(), ValidationMessage.LOGIN_SIZE_NOT_CORRECT);
        ValidationPredicates.lenght(10,50,userDto.getEmail(), ValidationMessage.EMAIL_SIZE_NOT_CORRECT);
        ValidationPredicates.emailMatcher(userDto.getEmail(),ValidationMessage.EMAIL_MATCH_NOT_CORRECT);
        ValidationPredicates.passwordRequirementsMatcher(userDto.getPassword(),ValidationMessage.PASSWORD_REGEX);
        ValidationPredicates.passwordMatcher(userDto.getPassword(),userDto.getConfirmedPassword(),ValidationMessage.CONFIRMED_PASSWORD_IS_DIFFERENT);
        System.out.println("Dodanie usera: "+userDto.getLogin()+" "+userDto.getName()+" "+userDto.getSecondname()+" "+userDto.getEmail()+" "+userDto.getPassword()+" "+userDto.getConfirmedPassword()+" "+userDto.getRoleType());
        UserEntity userEntity = usermapper.map(userDto);
        userRepository.save(userEntity);
    }

    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id);
        userRepository.delete(id);
    }

    @Override
    public void deleteAllUsers() {
        List<UserEntity> listofusers = userRepository.findAll();
        userRepository.deleteAll();
    }

    public UserViewDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id);
        return usermapper.map(userEntity);
    }

    public List<UserViewDto> getAllUsers(){
        List<UserEntity> listofusers = userRepository.findAll();
        return usermapper.map(listofusers);
    }
}
