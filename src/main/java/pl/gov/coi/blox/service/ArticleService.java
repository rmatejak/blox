package pl.gov.coi.blox.service;

import pl.gov.coi.blox.api.model.ArticleDto;
import pl.gov.coi.blox.api.model.RateDto;

public interface ArticleService {

    void addArticleToCategory(ArticleDto articleDto);
    void addRatingToArticle(RateDto rateDto);
}
