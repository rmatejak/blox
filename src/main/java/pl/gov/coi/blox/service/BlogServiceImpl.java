package pl.gov.coi.blox.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gov.coi.blox.api.mapper.BlogMapper;
import pl.gov.coi.blox.api.mapper.BlogMapperImpl;
import pl.gov.coi.blox.api.mapper.RateTypeMapperImpl;
import pl.gov.coi.blox.api.model.BlogDto;
import pl.gov.coi.blox.api.model.BlogViewDto;
import pl.gov.coi.blox.api.model.RateDto;
import pl.gov.coi.blox.entity.BlogEntity;
import pl.gov.coi.blox.entity.RateType;
import pl.gov.coi.blox.entity.UserEntity;
import pl.gov.coi.blox.repository.BlogRepository;
import pl.gov.coi.blox.repository.UserRepository;
import pl.gov.coi.blox.validation.EntityNotFoundException;
import pl.gov.coi.blox.validation.EntityNotFoundMassage;

@Service
@Transactional
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
                + blogDto.getDescription() + " "
                + blogDto.getStatus());
        UserEntity userEntity = userRepository.getOne(blogDto.getOwnerId());
        BlogEntity blogEntity = blogmapper.map(blogDto);
        userEntity.addBlog(blogEntity);
        blogRepository.save(blogEntity);
    }

    public BlogViewDto getBlogById(Long id) {
        BlogEntity blogEntity = blogRepository.findOne(id);
        return blogmapper.map(blogEntity);
    }

    public void addRatingToBlog(Long id, RateDto rateDto) {
        Preconditions.checkNotNull(rateDto, "Rate cannot be NULL");

        System.out.println("Dodanie oceny do bloga: "
                + rateDto.getRateType());
        BlogEntity blogEntity = blogRepository.getOne(id);
        blogEntity.addRate(RateType.valueOf(rateDto.getRateType().name()));
        System.out.println(blogEntity.getRating() + " " + blogEntity.getNumberOfRating()+ " " + blogEntity.getSumOfRates());
        blogRepository.save(blogEntity);
    }

    public void deleteBlogById(Long blogId) {
        Preconditions.checkNotNull(blogId, "blog cannot be null");

        BlogEntity blogEntity = blogRepository.getOne(blogId);
        UserEntity owner = blogEntity.getOwner();
        owner.removeBlog(blogEntity);
        blogRepository.delete(blogEntity);
    }



    public boolean isUserExist(Long id) {
        return userRepository.findById(id) != null;
    }
}
