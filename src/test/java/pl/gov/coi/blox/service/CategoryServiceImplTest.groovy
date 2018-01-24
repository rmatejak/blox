package pl.gov.coi.blox.service

import pl.gov.coi.blox.api.model.ArticleDto
import pl.gov.coi.blox.api.model.StatusDto
import pl.gov.coi.blox.entity.ArticleEntity
import pl.gov.coi.blox.entity.BlogEntity
import pl.gov.coi.blox.entity.CategoryEntity
import pl.gov.coi.blox.repository.ArticleRepository
import pl.gov.coi.blox.repository.CategoryRepository
import spock.lang.Specification

class CategoryServiceImplTest extends Specification {

    ArticleRepository articleRepository
    CategoryRepository categoryRepository
    CategoryServiceImpl categoryService

    void setup () {
        articleRepository = Mock(ArticleRepository)
        categoryRepository = Mock(CategoryRepository)

        categoryService = new CategoryServiceImpl(
                articleRepository,
                categoryRepository,
        )
    }

    def "should add Article to Category wit given Id"() {
        given:
        def categoryId = 1L
        def articleDto = new ArticleDto(
                author: 'Johny',
                topic: 'kodze z radoscia',
                text: 'auuuuu'
        )
        def categoryEntity = new CategoryEntity(
                owner: Mock(BlogEntity),
                name: 'KOD',
                description: 'informacje o kodzie',
                status: StatusDto.ACTIVE
        )
        and:
            categoryRepository.findById(categoryId) >> categoryEntity
        when:
            categoryService.addArticleToCategory(categoryId, articleDto)
        then:
            1 * articleRepository.save(_ as ArticleEntity)
            1 * categoryRepository.save(_ as CategoryEntity)
    }

    def "should return IllegalStateException"() {
        given:
            def categoryId = 1L
            def articleDto = new ArticleDto(
                    author: 'Johny',
                    topic: 'kodze z radoscia',
                    text: 'auuuuu'
            )
        and:
            categoryRepository.findById(categoryId) >> null
        when:
            categoryService.addArticleToCategory(categoryId, articleDto)
        then:
            thrown(BusinessException)
    }
}
