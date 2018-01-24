package pl.gov.coi.blox.service;


import pl.gov.coi.blox.api.model.BlogViewDto;
import pl.gov.coi.blox.api.model.CategoryDto;
import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.api.model.RateDto;

public interface BlogService {
    void addCategoryToBlog(Long id, CategoryDto categoryDto);
    void addCommentToBlog(Long id, CommentDto commentDto);
    BlogViewDto getBlogById(Long id);
    void addRatingToBlog(Long id, RateDto rateType);
    void deleteBlogById(Long id);
}
