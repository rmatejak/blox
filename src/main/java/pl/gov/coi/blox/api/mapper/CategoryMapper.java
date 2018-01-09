package pl.gov.coi.blox.api.mapper;
import org.mapstruct.Mapper;
import pl.gov.coi.blox.api.model.CategoryDto;
import pl.gov.coi.blox.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {
    CategoryEntity map(CategoryDto categoryDto);
}
