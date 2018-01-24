package pl.gov.coi.blox.service;

import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.api.model.RateDto;

public interface ArticleService {

    void addCommentToArticle(Long id, CommentDto commentDto);
    void addRatingToArticle(Long id, RateDto rateDto);
}
