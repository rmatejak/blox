package pl.gov.coi.blox.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gov.coi.blox.api.mapper.CommentMapper;
import pl.gov.coi.blox.api.mapper.CommentMapperImpl;
import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.entity.ArticleEntity;
import pl.gov.coi.blox.entity.BlogEntity;
import pl.gov.coi.blox.entity.CommentEntity;
import pl.gov.coi.blox.repository.ArticleRepository;
import pl.gov.coi.blox.repository.BlogRepository;
import pl.gov.coi.blox.repository.CommentRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ArticleRepository articleRepository;
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;
    private final static CommentMapper commentmapper = new CommentMapperImpl();

    public void addCommentToBlog(CommentDto commentDto){
        Preconditions.checkNotNull(commentDto, "Comment cannot be NULL");

        System.out.println("Dodanie komentarza (blog): "
                + commentDto.getOwnerId() + " "
                + commentDto.getContent());

        BlogEntity blogEntity = blogRepository.getOne(commentDto.getOwnerId());
        CommentEntity commentEntity = commentmapper.map(commentDto);
        blogEntity.addComment(commentEntity);
        commentRepository.save(commentEntity);
    }

    public void addCommentToArticle(CommentDto commentDto){
        Preconditions.checkNotNull(commentDto, "Comment cannot be NULL");

        System.out.println("Dodanie komentarza (artykuł): "
                + commentDto.getOwnerId() + " "
                + commentDto.getContent());

        ArticleEntity articleEntity = articleRepository.getOne(commentDto.getOwnerId());
        CommentEntity commentEntity = commentmapper.map(commentDto);
        articleEntity.addComment(commentEntity);
        commentRepository.save(commentEntity);
    }
}
