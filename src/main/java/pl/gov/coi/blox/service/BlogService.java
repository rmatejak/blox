package pl.gov.coi.blox.service;


import pl.gov.coi.blox.api.model.BlogDto;
import pl.gov.coi.blox.api.model.BlogViewDto;
import pl.gov.coi.blox.api.model.RateDto;
import pl.gov.coi.blox.api.model.RateTypeDto;
import pl.gov.coi.blox.entity.RateType;

public interface BlogService {
    void addBlogToUser (BlogDto blogDto);
    BlogViewDto getBlogById(Long id);
    void addRatingToBlog(Long id, RateDto rateType);
    void deleteBlogById(Long id);
}
