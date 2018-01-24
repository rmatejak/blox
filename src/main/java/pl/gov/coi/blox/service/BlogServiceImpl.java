package pl.gov.coi.blox.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gov.coi.blox.api.mapper.BlogMapper;
import pl.gov.coi.blox.api.mapper.BlogMapperImpl;
import pl.gov.coi.blox.api.mapper.CategoryMapper;
import pl.gov.coi.blox.api.mapper.CategoryMapperImpl;
import pl.gov.coi.blox.api.mapper.CommentMapper;
import pl.gov.coi.blox.api.mapper.CommentMapperImpl;
import pl.gov.coi.blox.api.model.BlogDto;
import pl.gov.coi.blox.api.model.BlogViewDto;
import pl.gov.coi.blox.api.model.CategoryDto;
import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.api.model.RateDto;
import pl.gov.coi.blox.entity.BlogEntity;
import pl.gov.coi.blox.entity.CategoryEntity;
import pl.gov.coi.blox.entity.CommentEntity;
import pl.gov.coi.blox.entity.RateType;
import pl.gov.coi.blox.entity.UserEntity;
import pl.gov.coi.blox.repository.BlogRepository;
import pl.gov.coi.blox.repository.CategoryRepository;
import pl.gov.coi.blox.repository.CommentRepository;
import pl.gov.coi.blox.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final CommentRepository commentRepository;
    private final CategoryRepository categoryRepository;
    private final BlogRepository blogRepository;
    private final static BlogMapper blogmapper = new BlogMapperImpl();
    private final static CategoryMapper categorymapper = new CategoryMapperImpl();
    private final static CommentMapper commentmapper = new CommentMapperImpl();


    @Override
    public void addCategoryToBlog(Long id, CategoryDto categoryDto) {
        Preconditions.checkNotNull(id,"Id cannot be NULL.");
        Preconditions.checkNotNull(categoryDto, "Category cannot be NULL.");

        CategoryEntity categoryEntity = categorymapper.map(categoryDto);
        BlogEntity blogEntity = blogRepository.findById(id);
        if (blogEntity == null) {
            throw new BusinessException("Blog not found");
        }
        blogEntity.addCategory(categoryEntity);
        categoryRepository.save(categoryEntity);
        blogRepository.save(blogEntity);
    }

    @Override
    public void addCommentToBlog(Long id, CommentDto commentDto) {
        Preconditions.checkNotNull(id,"Id cannot be NULL.");
        Preconditions.checkNotNull(commentDto, "CommentDto cannot be NULL.");

        CommentEntity commentEntity = commentmapper.map(commentDto);
        BlogEntity blogEntity = blogRepository.findById(id);
        if (blogEntity == null) {
            throw new BusinessException("Blog not found");
        }
        blogEntity.addComment(commentEntity);
        commentRepository.save(commentEntity);
        blogRepository.save(blogEntity);
    }

    @Override
    public BlogViewDto getBlogById(Long id) {
        BlogEntity blogEntity = blogRepository.findById(id);
        if (blogEntity == null) {
            throw new BusinessException("Blog not found");
        }
        BlogViewDto map = blogmapper.map(blogEntity);
        map.setOwnerId(blogEntity.getOwner().getId());
        return map;
    }

    @Override
    public void addRatingToBlog(Long id, RateDto rateType) {
        Preconditions.checkNotNull(id,"Id cannot be NULL.");
        Preconditions.checkNotNull(rateType, "Rate cannot be NULL");

        System.out.println("Dodanie oceny do bloga: "
                + rateType.getRateType());
        BlogEntity blogEntity = blogRepository.getOne(id);
        blogEntity.addRate(RateType.valueOf(rateType.getRateType().name()));
        System.out.println(blogEntity.getRating() + " " + blogEntity.getNumberOfRating()+ " " + blogEntity.getSumOfRates());
        blogRepository.save(blogEntity);
    }

    @Override
    public void deleteBlogById(Long id) {
        Preconditions.checkNotNull(id, "blog cannot be null");

        BlogEntity blogEntity = blogRepository.findById(id);
        if (blogEntity == null){
            throw new BusinessException("Blog not found");
        }
        UserEntity owner = blogEntity.getOwner();
        owner.removeBlog(blogEntity);
        blogRepository.delete(blogEntity);
    }
}
