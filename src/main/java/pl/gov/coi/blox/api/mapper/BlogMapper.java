package pl.gov.coi.blox.api.mapper;

import org.mapstruct.Mapper;
import pl.gov.coi.blox.entity.BlogDto;
import pl.gov.coi.blox.entity.BlogViewDto;
import pl.gov.coi.blox.model.BlogEntity;

@Mapper
public interface BlogMapper {
    BlogEntity map(BlogDto blogDto);
    BlogViewDto map (BlogEntity blogEntity);
}
