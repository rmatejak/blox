package pl.gov.coi.blox.api.controller;

import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gov.coi.blox.api.BlogsApi;
import pl.gov.coi.blox.api.model.BlogDto;
import pl.gov.coi.blox.api.model.BlogViewDto;
import pl.gov.coi.blox.api.model.CategoryDto;
import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.api.model.RateDto;
import pl.gov.coi.blox.api.model.RateTypeDto;
import pl.gov.coi.blox.api.model.UserViewDto;
import pl.gov.coi.blox.service.BlogService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlogRestController implements BlogsApi {

    private final BlogService blogService;


    @Override
    public ResponseEntity<Void> addCategoryToBlog(@Valid @PathVariable Long id,@Valid @RequestBody CategoryDto categoryDto) {
        blogService.addCategoryToBlog(id, categoryDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addCommentToBlog(@Valid @PathVariable Long id,@Valid @RequestBody CommentDto blogComment) {
        blogService.addCommentToBlog(id, blogComment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addRatingToBlog(@Valid @PathVariable Long id,@Valid @RequestBody RateDto rateBlog) {
        blogService.addRatingToBlog(id, rateBlog);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteBlogById(@Valid @PathVariable Long id) {
        blogService.deleteBlogById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BlogViewDto> getBlogById(@Valid @PathVariable Long id) {
        return new ResponseEntity<BlogViewDto>(blogService.getBlogById(id), HttpStatus.OK);
    }
}
