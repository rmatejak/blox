package pl.gov.coi.blox.api.Mapper;

import org.mapstruct.Mapper;
import pl.gov.coi.blox.entity.UserDto;
import pl.gov.coi.blox.entity.UserViewDto;
import pl.gov.coi.blox.model.UserEntity;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserMapper {
    UserEntity map(UserDto userDto);
    UserViewDto map(UserEntity userEntity);
    List<UserViewDto> map(Collection<UserEntity> users);
}
