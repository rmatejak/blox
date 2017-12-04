package pl.gov.coi.blox.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.blox.api.mapper.BlogMapper;
import pl.gov.coi.blox.api.mapper.BlogMapperImpl;
import pl.gov.coi.blox.entity.BlogDto;
import pl.gov.coi.blox.entity.BlogViewDto;
import pl.gov.coi.blox.model.BlogEntity;
import pl.gov.coi.blox.model.UserEntity;
import pl.gov.coi.blox.repository.BlogRepository;
import pl.gov.coi.blox.repository.UserRepository;
import pl.gov.coi.blox.validation.EntityNotFoundException;
import pl.gov.coi.blox.validation.EntityNotFoundMassage;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final static BlogMapper blogmapper = new BlogMapperImpl();

    public void addBlogToUser(BlogDto blogDto) {
        Preconditions.checkNotNull(blogDto, "Blog cannot be NULL");
        if(!(isUserExist(blogDto.getOwnerId()))){
            throw new EntityNotFoundException(EntityNotFoundMassage.USER_DOES_NOT_EXIST);
        }

        System.out.println("Dodanie bloga: "
                + blogDto.getBlogType() + " "
                + blogDto.getRateType() + " "
                + blogDto.getDescription() + " "
                + blogDto.getActive());
        UserEntity userEntity = userRepository.getOne(blogDto.getOwnerId());
        BlogEntity blogEntity = blogmapper.map(blogDto);
        userEntity.addBlog(blogEntity);
        blogRepository.save(blogEntity);
    }

    public BlogViewDto getBlogById(Long id) {
        BlogEntity blogEntity = blogRepository.findById(id);
        return blogmapper.map(blogEntity);
    }

    public boolean isUserExist(Long id) {
        return userRepository.findById(id) != null;
    }
}
