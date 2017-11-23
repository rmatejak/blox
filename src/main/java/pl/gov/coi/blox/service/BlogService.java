package pl.gov.coi.blox.service;
import pl.gov.coi.blox.entity.BlogDto;
import pl.gov.coi.blox.entity.BlogViewDto;


public interface BlogService {
    void addBlogToUser (BlogDto blogDto);
    BlogViewDto getBlogById(Long id);
}
