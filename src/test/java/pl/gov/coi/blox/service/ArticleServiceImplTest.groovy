package pl.gov.coi.blox.service

import pl.gov.coi.blox.api.model.CommentDto
import pl.gov.coi.blox.api.model.RateDto
import pl.gov.coi.blox.api.model.RateTypeDto
import pl.gov.coi.blox.entity.ArticleEntity
import pl.gov.coi.blox.entity.CategoryEntity
import pl.gov.coi.blox.entity.CommentEntity
import pl.gov.coi.blox.entity.RateType
import pl.gov.coi.blox.repository.ArticleRepository
import pl.gov.coi.blox.repository.CommentRepository
import spock.lang.Specification

class ArticleServiceImplTest extends Specification {

    CommentRepository commentRepository
    ArticleRepository articleRepository
    ArticleServiceImpl articleService

    void setup() {
        commentRepository = Mock(CommentRepository)
        articleRepository = Mock(ArticleRepository)

        articleService = new ArticleServiceImpl(
                commentRepository,
                articleRepository
        )
    }

    def "should add comment to article with given id"() {
        given:
            def articleId = 1L
            def commentDto = new CommentDto(
                    content: 'Tresc komentarza'
            )
            def articleEntity = new ArticleEntity(
                    owner: Mock(CategoryEntity),
                    author: 'John',
                    topic: 'Java',
                    text: 'content',
                    rateType: RateType.FAIL,
                    rating: 123L,
                    sumOfRates: 1234L,
                    numberOfRating: 2L
            )
        and:
            articleRepository.findById(articleId) >> articleEntity
        when:
            articleService.addCommentToArticle(articleId, commentDto)
        then:
            1 * commentRepository.save(_ as CommentEntity)
            1 * articleRepository.save(_ as ArticleEntity)
    }

    def "addCommentToArticle should throw IllegalStateException"() {
        given:
            def articleId = 1L
            def commentDto = new CommentDto(
                    content: 'Tresc komentarza'
            )
        and:
            articleRepository.findById(articleId) >> null
        when:
            articleService.addCommentToArticle(articleId, commentDto)
        then:
            thrown(BusinessException)
    }

    def "should add rate to article with given id"() {
        given:
            def articleId = 1L
            def rateDto = new RateDto(
                    rateType: RateTypeDto.FAIL
            )
            def articleEntity = new ArticleEntity(
                    owner: Mock(CategoryEntity),
                    author: 'John',
                    topic: 'Java',
                    text: 'content',
                    rateType: RateType.FAIL,
                    rating: 123L,
                    sumOfRates: 1234L,
                    numberOfRating: 2L
            )
        and:
            articleRepository.findById(articleId) >> articleEntity
        when:
            articleService.addRatingToArticle(articleId, rateDto)
        then:
            1 * articleRepository.save(articleEntity)
    }

    def "addRatingToArticle should throw IllegalStateException"() {
        given:
            def articleId = 1L
            def rateDto = new RateDto(
                    rateType: RateTypeDto.FAIL
            )
        and:
            articleRepository.findById(articleId) >> null
        when:
            articleService.addRatingToArticle(articleId, rateDto)
        then:
            thrown(BusinessException)
    }
}
