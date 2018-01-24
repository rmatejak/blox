package pl.gov.coi.blox.service;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gov.coi.blox.api.mapper.ArticleMapper;
import pl.gov.coi.blox.api.mapper.ArticleMapperImpl;
import pl.gov.coi.blox.api.model.ArticleDto;
import pl.gov.coi.blox.entity.ArticleEntity;
import pl.gov.coi.blox.entity.CategoryEntity;
import pl.gov.coi.blox.repository.ArticleRepository;
import pl.gov.coi.blox.repository.CategoryRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final static ArticleMapper articlemapper= new ArticleMapperImpl();


    @Override
    public void addArticleToCategory(Long id, ArticleDto articleDto) {
        Preconditions.checkNotNull(id,"Id cannot be NULL.");
        Preconditions.checkNotNull(articleDto, "articleDto cannot be NULL.");

        CategoryEntity categoryEntity = categoryRepository.findById(id);
        if (categoryEntity == null) {
            throw new BusinessException("Category not found");
        }
        ArticleEntity articleEntity = articlemapper.map(articleDto);
        categoryEntity.addArticle(articleEntity);
        articleRepository.save(articleEntity);
        categoryRepository.save(categoryEntity);
    }
}
