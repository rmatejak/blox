package pl.gov.coi.blox.api.mapper;

import org.mapstruct.Mapper;
import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.entity.CommentEntity;

@Mapper
public interface CommentMapper {
    CommentEntity map(CommentDto commentDto);
}
