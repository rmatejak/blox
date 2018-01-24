package pl.gov.coi.blox.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gov.coi.blox.api.ArticleApi;
import pl.gov.coi.blox.api.model.ArticleDto;
import pl.gov.coi.blox.api.model.CommentDto;
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
    public ResponseEntity<Void> addCommentToArticle(@Valid @PathVariable Long id, @Valid @RequestBody CommentDto articleComment) {
        articleService.addCommentToArticle(id, articleComment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addRatingToArticle(@Valid @PathVariable Long id,@Valid @RequestBody RateDto rateArticle) {
        articleService.addRatingToArticle(id, rateArticle);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
