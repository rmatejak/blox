package pl.gov.coi.blox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.blox.api.Mapper.BlogMapper;
import pl.gov.coi.blox.api.Mapper.BlogMapperImpl;
import pl.gov.coi.blox.entity.BlogDto;
import pl.gov.coi.blox.entity.BlogViewDto;
import pl.gov.coi.blox.model.BlogEntity;
import pl.gov.coi.blox.repository.BlogRepository;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final static BlogMapper blogmapper = new BlogMapperImpl();

    public void createBlog(BlogDto blogDto) {
        System.out.println("Dodanie bloga: "
                + blogDto.getBlogType() + " "
                + blogDto.getRateType() + " "
                + blogDto.getDescription() + " "
                + blogDto.getActive());
        BlogEntity blogEntity = blogmapper.map(blogDto);
        blogRepository.save(blogEntity);
    }

    public BlogViewDto getBlogById(Long id) {
        BlogEntity blogEntity = blogRepository.findById(id);
        return blogmapper.map(blogEntity);
    }
}
