package pl.gov.coi.blox.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gov.coi.blox.api.mapper.CommentMapper;
import pl.gov.coi.blox.api.mapper.CommentMapperImpl;
import pl.gov.coi.blox.api.model.CommentDto;
import pl.gov.coi.blox.api.model.RateDto;
import pl.gov.coi.blox.entity.ArticleEntity;
import pl.gov.coi.blox.entity.CommentEntity;
import pl.gov.coi.blox.entity.RateType;
import pl.gov.coi.blox.repository.ArticleRepository;
import pl.gov.coi.blox.repository.CommentRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final static CommentMapper commentmapper = new CommentMapperImpl();


    @Override
    public void addCommentToArticle(Long id, CommentDto commentDto) {
        Preconditions.checkNotNull(id,"Id cannot be NULL.");
        Preconditions.checkNotNull(commentDto, "commentDto cannot be NULL.");

        CommentEntity commentEntity = commentmapper.map(commentDto);
        ArticleEntity articleEntity = articleRepository.findById(id);
        if (articleEntity == null) {
            throw new BusinessException("Article not found");
        }
        articleEntity.addComment(commentEntity);
        commentRepository.save(commentEntity);
        articleRepository.save(articleEntity);
    }

    @Override
    public void addRatingToArticle(Long id, RateDto rateDto) {
        Preconditions.checkNotNull(id,"Id cannot be NULL.");
        Preconditions.checkNotNull(rateDto, "ateDto cannot be NULL");

        System.out.println("Dodanie oceny do artykułu: "
                + rateDto.getRateType());
        ArticleEntity articleEntity = articleRepository.findById(id);
        if (articleEntity == null) {
            throw new BusinessException("Article not found");
        }
        articleEntity.addRate(RateType.valueOf(rateDto.getRateType().name()));
        System.out.println(articleEntity.getRating() + " " + articleEntity.getNumberOfRating()+ " " + articleEntity.getSumOfRates());
        articleRepository.save(articleEntity);
    }
}

