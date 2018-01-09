package pl.gov.coi.blox.api.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gov.coi.blox.api.CategoryApi;
import pl.gov.coi.blox.api.model.CategoryDto;
import pl.gov.coi.blox.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryRestController implements CategoryApi{

    private final CategoryService categoryService;


    @Override
    public ResponseEntity<Void> addCategoryToBlog(@Valid @RequestBody CategoryDto categoryDto) {
        categoryService.addCategoryToBlog(categoryDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
