package pl.gov.coi.blox.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gov.coi.blox.api.CommentApi;
import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.service.CommentService;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentRestController implements CommentApi{

    private final CommentService commentService;


    @Override
    public ResponseEntity<Void> addCommentToArticle(@Valid @RequestBody CommentDto articleComment) {
        commentService.addCommentToArticle(articleComment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addCommentToBlog(@Valid @RequestBody CommentDto blogComment) {
        commentService.addCommentToBlog(blogComment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
