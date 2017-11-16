package pl.gov.coi.blox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.blox.api.Mapper.UserMapper;
import pl.gov.coi.blox.api.Mapper.UserMapperImpl;
import pl.gov.coi.blox.api.UserRepository;
import pl.gov.coi.blox.entity.UserDto;
import pl.gov.coi.blox.entity.UserViewDto;
import pl.gov.coi.blox.repository.model.UserEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final static UserMapper usermapper = new UserMapperImpl();

    public void addUser(UserDto userDto) {
        System.out.println("Dodanie usera: "+userDto.getLogin()+" "+userDto.getUsername()+" "+userDto.getEmail()+" "+userDto.getPassword());
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
