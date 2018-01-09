package pl.gov.coi.blox.service;
import pl.gov.coi.blox.api.model.CommentDto;

public interface CommentService {

    void addCommentToBlog(CommentDto commentDto);
    void addCommentToArticle(CommentDto commentDto);
}
