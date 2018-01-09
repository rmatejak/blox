package pl.gov.coi.blox.service;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gov.coi.blox.api.mapper.CategoryMapper;
import pl.gov.coi.blox.api.mapper.CategoryMapperImpl;
import pl.gov.coi.blox.api.model.CategoryDto;
import pl.gov.coi.blox.entity.BlogEntity;
import pl.gov.coi.blox.entity.CategoryEntity;
import pl.gov.coi.blox.repository.BlogRepository;
import pl.gov.coi.blox.repository.CategoryRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    private final static CategoryMapper categorymapper = new CategoryMapperImpl();

    public void addCategoryToBlog(CategoryDto categoryDto){
        Preconditions.checkNotNull(categoryDto, "Category cannot be NULL");

        System.out.println("Dodanie kategorii: "
                + categoryDto.getBlogId() + " "
                + categoryDto.getName() + " "
                + categoryDto.getDescription());
        BlogEntity blogEntity = blogRepository.getOne(categoryDto.getBlogId());
        CategoryEntity categoryEntity = categorymapper.map(categoryDto);
        blogEntity.addCategory(categoryEntity);
        categoryRepository.save(categoryEntity);
    }
}
