package pl.gov.coi.blox.service

import org.apache.catalina.User
import org.junit.Test
import pl.gov.coi.blox.api.model.BlogType
import pl.gov.coi.blox.api.model.BlogViewDto
import pl.gov.coi.blox.api.model.CategoryDto
import pl.gov.coi.blox.api.model.CommentDto
import pl.gov.coi.blox.api.model.StatusDto
import pl.gov.coi.blox.api.model.UserViewDto
import pl.gov.coi.blox.entity.BlogEntity
import pl.gov.coi.blox.entity.CategoryEntity
import pl.gov.coi.blox.entity.CommentEntity
import pl.gov.coi.blox.entity.RateType
import pl.gov.coi.blox.entity.RoleType
import pl.gov.coi.blox.entity.UserEntity
import pl.gov.coi.blox.repository.BlogRepository
import pl.gov.coi.blox.repository.CategoryRepository
import pl.gov.coi.blox.repository.CommentRepository
import spock.lang.Specification

import static org.junit.Assert.assertEquals
import static org.mockito.Mockito.when

class BlogServiceImplTest extends Specification {

    CommentRepository commentRepository
    CategoryRepository categoryRepository
    BlogRepository blogRepository
    BlogServiceImpl blogService

    void setup() {
        commentRepository = Mock(CommentRepository)
        categoryRepository = Mock(CategoryRepository)
        blogRepository = Mock(BlogRepository)

        blogService = new BlogServiceImpl(
                commentRepository,
                categoryRepository,
                blogRepository)
    }

    def "Should add Category to Blog with given Id"() {
        given:
            def blogId = 1L
            def categoryDto = new CategoryDto(
                    name: 'Johny',
                    description: 'blabla',
                    status: StatusDto.ACTIVE
            )

            def blogEntity = new BlogEntity(
                    owner: Mock(UserEntity),
                    rateType: RateType.FAIL,
                    rating: 5L,
                    blogType: BlogType.KOD,
                    description: 'eloelo',
                    status: StatusDto.ACTIVE,
                    sumOfRates: 2L,
                    numberOfRating: 5L
            )
        and:
            blogRepository.findById(blogId) >> blogEntity
        when:
            blogService.addCategoryToBlog(blogId, categoryDto)
        then:
            1 * categoryRepository.save(_ as CategoryEntity)
            1 * blogRepository.save(_ as BlogEntity)
    }

    def "addCategoryToBlog should throw IllegalStateException"() {
        given:
            def blogId = 1L
            def categoryDto = new CategoryDto(
                    name: 'Johny',
                    description: 'blabla',
                    status: StatusDto.ACTIVE
            )

        and:
            blogRepository.findById(blogId) >> null
        when:
            blogService.addCategoryToBlog(blogId, categoryDto)
        then:
            thrown(BusinessException)
    }

    def "should add comment to Blog with given Id"() {
        given:
            def blogId = 1L
            def commentDto = new CommentDto(
                    content: 'Tresc komentarza'
            )
            def blogEntity = new BlogEntity(
                    owner: Mock(UserEntity),
                    rateType: RateType.FAIL,
                    rating: 5L,
                    blogType: BlogType.KOD,
                    description: 'eloelo',
                    status: StatusDto.ACTIVE,
                    sumOfRates: 2L,
                    numberOfRating: 5L
            )
        and:
            blogRepository.findById(blogId) >> blogEntity
        when:
            blogService.addCommentToBlog(blogId, commentDto)
        then:
            1 * commentRepository.save(_ as CommentEntity)
            1 * blogRepository.save(_ as BlogEntity)
    }

    def "addCommentToBlog should throw IllegalStateException"() {
        given:
            def blogId = 1L
            def commentDto = new CommentDto(
                    content: 'Tresc komentarza'
            )
        and:
            blogRepository.findById(blogId) >> null
        when:
            blogService.addCommentToBlog(blogId, commentDto)
        then:
            thrown(BusinessException)
    }

    def "getBlogById should return BlogEntity with given Id"() {
        given:
            def blogId = 1L
            def blogEntity = new BlogEntity(
                    owner: Mock(UserEntity),
                    rateType: RateType.FAIL,
                    rating: 5L,
                    blogType: BlogType.KOD,
                    description: 'eloelo',
                    status: StatusDto.ACTIVE,
                    sumOfRates: 2L,
                    numberOfRating: 5L
            )
        and:
            blogRepository.findById(blogId) >> blogEntity
        when:
            def blogViewDto = blogService.getBlogById(blogId)
        then:
            blogViewDto != null
            blogViewDto.ownerId == blogEntity.getOwner().getId()
            blogViewDto.getRateType() != null
            blogViewDto.getBlogType() == blogEntity.getBlogType()
            blogViewDto.getDescription() == blogEntity.getDescription()
            blogViewDto.getStatus() == blogEntity.getStatus()
    }

    def "Should throw IllegalStateException"() {
        given:
            def blogId = 1L
        and:
            blogRepository.findById(blogId) >> null
        when:
            blogService.getBlogById(blogId)
        then:
            thrown(BusinessException)
    }

    def "deleteBlogById should delete blog with given Id"() {
        given:
            def blogId = 1L
            def blogEntity = new BlogEntity(
                    owner: null,
                    rateType: RateType.FAIL,
                    rating: 5L,
                    blogType: BlogType.KOD,
                    description: 'eloelo',
                    status: StatusDto.ACTIVE,
                    sumOfRates: 2L,
                    numberOfRating: 5L
            )
            def userEntity = new UserEntity(
                    blogs: (List) [blogEntity],
                    name: 'Konrad',
                    secondName: 'Nysa',
                    login: 'jkowalski',
                    password: '123qwe@Q',
                    confirmedPassword: '123qwe@Q',
                    email: 'jan.kowalski@poczta.pl',
                    roleType: RoleType.USER
            )
        blogEntity.setOwner(userEntity)
        and:
            blogRepository.findById(blogId) >> blogEntity
            userEntity.addBlog(blogEntity)
        when:
            blogService.deleteBlogById(blogId)
        then:
            !userEntity.getBlogs().contains(blogEntity)
            blogEntity.getOwner() == null

    }

    def "deleteBlogById should throw IllegalStateException"() {
        given:
            def blogId = 1L
            def blogEntity = new BlogEntity(
                    owner: null,
                    rateType: RateType.FAIL,
                    rating: 5L,
                    blogType: BlogType.KOD,
                    description: 'eloelo',
                    status: StatusDto.ACTIVE,
                    sumOfRates: 2L,
                    numberOfRating: 5L
            )
            def userEntity = new UserEntity(
                    blogs: (List) [blogEntity],
                    name: 'Konrad',
                    secondName: 'Nysa',
                    login: 'jkowalski',
                    password: '123qwe@Q',
                    confirmedPassword: '123qwe@Q',
                    email: 'jan.kowalski@poczta.pl',
                    roleType: RoleType.USER
            )
        blogEntity.setOwner(userEntity)
        and:
            blogRepository.findById(blogId) >> null
        when:
            blogService.deleteBlogById(blogId)
        then:
            thrown(BusinessException)
    }
}
