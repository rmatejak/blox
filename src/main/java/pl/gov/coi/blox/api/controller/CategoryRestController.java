package pl.gov.coi.blox.api.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gov.coi.blox.api.CategoryApi;
import pl.gov.coi.blox.api.model.ArticleDto;
import pl.gov.coi.blox.api.model.CategoryDto;
import pl.gov.coi.blox.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryRestController implements CategoryApi{

    private final CategoryService categoryService;


    @Override
    public ResponseEntity<Void> addArticleToCategory(@Valid @PathVariable Long id,@Valid @RequestBody ArticleDto newArticle) {
        categoryService.addArticleToCategory(id, newArticle);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
