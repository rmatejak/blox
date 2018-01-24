package pl.gov.coi.blox.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import pl.gov.coi.blox.api.model.BlogDto;
import pl.gov.coi.blox.api.model.BlogViewDto;
import pl.gov.coi.blox.api.model.RateTypeDto;
import pl.gov.coi.blox.api.model.UserViewDto;
import pl.gov.coi.blox.entity.BlogEntity;
import pl.gov.coi.blox.entity.RateType;
import pl.gov.coi.blox.entity.UserEntity;

@Mapper
public interface BlogMapper {

    BlogEntity map(BlogDto blogDto);
    BlogViewDto map(BlogEntity blogEntity);
}
