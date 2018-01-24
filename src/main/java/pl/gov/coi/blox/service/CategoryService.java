package pl.gov.coi.blox.service;
import pl.gov.coi.blox.api.model.ArticleDto;

public interface CategoryService {
    void addArticleToCategory(Long id, ArticleDto articleDto);
}
