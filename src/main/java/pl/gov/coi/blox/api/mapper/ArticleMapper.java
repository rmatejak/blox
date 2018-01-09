package pl.gov.coi.blox.api.mapper;

import org.mapstruct.Mapper;
import pl.gov.coi.blox.api.model.ArticleDto;
import pl.gov.coi.blox.entity.ArticleEntity;
@Mapper
public interface ArticleMapper {
    ArticleEntity map(ArticleDto articleDto);
}
