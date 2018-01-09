package pl.gov.coi.blox.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gov.coi.blox.api.mapper.ArticleMapper;
import pl.gov.coi.blox.api.mapper.ArticleMapperImpl;
import pl.gov.coi.blox.api.model.ArticleDto;
import pl.gov.coi.blox.api.model.RateDto;
import pl.gov.coi.blox.entity.ArticleEntity;
import pl.gov.coi.blox.entity.CategoryEntity;
import pl.gov.coi.blox.repository.ArticleRepository;
import pl.gov.coi.blox.repository.CategoryRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final static ArticleMapper articlemapper = new ArticleMapperImpl();

    public void addArticleToCategory(ArticleDto articleDto){
        Preconditions.checkNotNull(articleDto, "Article cannot be NULL");

        System.out.println("Dodanie artykułu: "
        + articleDto.getCategoryId() + " "
                + articleDto.getAuthor() + " "
                + articleDto.getTopic() + " "
                + articleDto.getText());
        CategoryEntity categoryEntity = categoryRepository.getOne(articleDto.getCategoryId());
        ArticleEntity articleEntity = articlemapper.map(articleDto);
        categoryEntity.addArticle(articleEntity);
        articleRepository.save(articleEntity);
    }

    public void addRatingToArticle(RateDto rateDto) {
        /*Preconditions.checkNotNull(rateDto, "Rate cannot be NULL");

        System.out.println("Dodanie oceny do artykułu: "
                + rateDto.getRateType());
        ArticleEntity articleEntity = articleRepository.getOne(rateDto.getOwnerId());
       // articleEntity.addRate(rateDto.getRateType());
        System.out.println(articleEntity.getRating() + " " + articleEntity.getNumberOfRating()+ " " + articleEntity.getSumOfRates());
        articleRepository.save(articleEntity);*/
    }
}
