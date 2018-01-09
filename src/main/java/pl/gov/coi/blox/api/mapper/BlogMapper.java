package pl.gov.coi.blox.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import pl.gov.coi.blox.api.model.BlogDto;
import pl.gov.coi.blox.api.model.BlogViewDto;
import pl.gov.coi.blox.api.model.RateTypeDto;
import pl.gov.coi.blox.entity.BlogEntity;
import pl.gov.coi.blox.entity.RateType;

@Mapper
public interface BlogMapper {

    @Mapping(target = "rateType", qualifiedByName = "mapRateType")
    BlogEntity map(BlogDto blogDto);
    BlogViewDto map(BlogEntity blogEntity);

    default RateType mapRateType(RateType rateTypeDto) {
        return null;
    }

}
