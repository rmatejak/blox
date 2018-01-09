package pl.gov.coi.blox.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.gov.coi.blox.api.model.UserDto;
import pl.gov.coi.blox.api.model.UserViewDto;
import pl.gov.coi.blox.entity.UserEntity;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserMapper {
    UserEntity map(UserDto userDto);
    UserViewDto map(UserEntity userEntity);
    List<UserViewDto> map(Collection<UserEntity> users);
    UserEntity map(UserDto userDto, @MappingTarget UserEntity userEntity);
}
