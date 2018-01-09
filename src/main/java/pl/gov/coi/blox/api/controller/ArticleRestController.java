package pl.gov.coi.blox.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gov.coi.blox.api.ArticleApi;
import pl.gov.coi.blox.api.model.ArticleDto;
import pl.gov.coi.blox.api.model.RateDto;
import pl.gov.coi.blox.service.ArticleService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleRestController implements ArticleApi {

    private final ArticleService articleService;

    @Override
    public ResponseEntity<Void> addArticleToCategory(@Valid @RequestBody ArticleDto newArticle) {
        articleService.addArticleToCategory(newArticle);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<Void> addRatingToArticle(@Valid @RequestBody RateDto rateArticle) {
        articleService.addRatingToArticle(rateArticle);
        return new ResponseEntity<>(OK);
    }
}
