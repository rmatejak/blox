package pl.gov.coi.blox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.blox.api.NewUserDto;
import pl.gov.coi.blox.repository.UserEntity;
import pl.gov.coi.blox.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser(NewUserDto userDto) {
        System.out.println("Dodany zostanie user o loginie: " + userDto.getLogin());
        System.out.println("Dodany zostanie user o hasle: " + userDto.getPassword());
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDto.getLogin());
        userEntity.setPassword(userDto.getPassword());
        userRepository.save(userEntity);
    }

}
