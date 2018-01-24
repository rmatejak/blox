package pl.gov.coi.blox.service

import pl.gov.coi.blox.api.model.BlogDto
import pl.gov.coi.blox.api.model.BlogType
import pl.gov.coi.blox.api.model.CommentDto
import pl.gov.coi.blox.api.model.StatusDto
import pl.gov.coi.blox.api.model.UserDto
import pl.gov.coi.blox.api.model.UserViewDto
import pl.gov.coi.blox.entity.BlogEntity
import pl.gov.coi.blox.entity.CommentEntity
import pl.gov.coi.blox.entity.RoleType
import pl.gov.coi.blox.entity.UserEntity
import pl.gov.coi.blox.repository.BlogRepository
import pl.gov.coi.blox.repository.CommentRepository
import pl.gov.coi.blox.repository.UserRepository
import spock.lang.Specification

class UserServiceImplTest extends Specification {

    CommentRepository commentRepository
    UserRepository userRepository
    BlogRepository blogRepository
    UserServiceImpl userService

    void setup() {
        commentRepository = Mock(CommentRepository)
        userRepository = Mock(UserRepository)
        blogRepository = Mock(BlogRepository)

        userService = new UserServiceImpl(
                commentRepository,
                userRepository,
                blogRepository
        )
    }

    def "Should add user to userRepository"() {
        given:
            def userDto = new UserDto(
                    name: 'Jan',
                    secondName: 'Kowalski',
                    login: 'jkowalski',
                    email: 'jan.kowalski@poczta.fm',
                    password: '123qwe@Q',
                    confirmedPassword: '123qwe@Q',
                    roleType: UserDto.RoleTypeEnum.ADMIN
            )
        when:
            userService.addUser(userDto)
        then:
            1 * userRepository.save(_ as UserEntity)
    }

    def "Should add Blog to User with given Id"() {
        given:
            def userId = 1L
            def blogDto = new BlogDto(
                    blogType: BlogType.KOD,
                    description: 'xyz',
                    status: StatusDto.ACTIVE
            )
            def userEntity = new UserEntity(
                    name: 'Konrad',
                    secondName: 'Nysa',
                    login: 'jkowalski',
                    password: '123qwe@Q',
                    confirmedPassword: '123qwe@Q',
                    email: 'jan.kowalski@poczta.pl',
                    roleType: RoleType.USER
            )
        and:
            userRepository.findById(userId) >> userEntity
        when:
            userService.addBlogToUser(userId, blogDto)
        then:
            1 * blogRepository.save(_ as BlogEntity)
            1 * userRepository.save(_ as UserEntity)
    }

    def "addBlogToUser should throw IllegalStateException"() {
        given:

            def userId = 1L
            def blogDto = new BlogDto(
                    blogType: BlogType.KOD,
                    description: 'xyz',
                    status: StatusDto.ACTIVE
            )
        and:
            userRepository.findById(userId) >> null
        when:
            userService.addBlogToUser(userId, blogDto)
        then:
            thrown(BusinessException)
    }

    def "Should add comment to user with given Id"() {
        given:
            def userId = 1L
            def commentDto = new CommentDto(
                    content: 'dobra zmiana'
            )
            def userEntity = new UserEntity(
                    name: 'Konrad',
                    secondName: 'Nysa',
                    login: 'jkowalski',
                    password: '123qwe@Q',
                    confirmedPassword: '123qwe@Q',
                    email: 'jan.kowalski@poczta.pl',
                    roleType: RoleType.USER
            )
        and:
            userRepository.findById(userId) >> userEntity
        when:
            userService.addCommentToUser(userId, commentDto)
        then:
            1 * commentRepository.save(_ as CommentEntity)
            1 * userRepository.save(_ as UserEntity)
    }

    def "addCommentToUser should throw IllegalStateException"() {
        given:
            def userId = 1L
            def commentDto = new CommentDto(
                    content: 'dobra zmiana'
            )
        and:
            userRepository.findById(userId) >> null
        when:
            userService.addCommentToUser(userId, commentDto)
        then:
            thrown(BusinessException)
    }

    def "Should return User with given Id"() {
        given:
            def userId = 1L
            def userEntity = new UserEntity(
                    name: 'Konrad',
                    secondName: 'Nysa',
                    login: 'jkowalski',
                    password: '123qwe@Q',
                    confirmedPassword: '123qwe@Q',
                    email: 'jan.kowalski@poczta.pl',
                    roleType: RoleType.USER
            )
        and:
            userRepository.findById(userId) >> userEntity
        when:
            def userViewDto = userService.getUserById(userId)
        then:
            userViewDto != null
            userViewDto.getId() == userEntity.getId()
            userViewDto.getEmail() == userEntity.getEmail()
            userViewDto.getLogin() == userEntity.getLogin()
            userViewDto.getName() == userEntity.getName()
            userViewDto.getSecondName() == userEntity.getSecondName()
    }

    def "getUserById should throw IllegalStateException"() {
        given:
            def userId = 1L
            def userEntity = new UserEntity(
                    name: 'Konrad',
                    secondName: 'Nysa',
                    login: 'jkowalski',
                    password: '123qwe@Q',
                    confirmedPassword: '123qwe@Q',
                    email: 'jan.kowalski@poczta.pl',
                    roleType: RoleType.USER
            )
        and:
            userRepository.findById(userId) >> null
        when:
            userService.getUserById(userId)
        then:
            thrown(BusinessException)
    }

    def "Should return list of Users"() {
        given:
            def userEntity = new UserEntity(
                    name: 'Konrad',
                    secondName: 'Nysa',
                    login: 'jkowalski',
                    password: '123qwe@Q',
                    confirmedPassword: '123qwe@Q',
                    email: 'jan.kowalski@poczta.pl',
                    roleType: RoleType.USER
            )
            def userEntity2 = new UserEntity(
                    name: 'Romek',
                    secondName: 'Domek',
                    login: 'rdomek',
                    password: '789qwe@Q',
                    confirmedPassword: '789qwe@Q',
                    email: 'romek.domek@poczta.pl',
                    roleType: RoleType.USER
            )
        and:
         userRepository.findAll() >> (List) [userEntity, userEntity2]
        when:
            def users = userService.getAllUsers()
        then:
            users != null
            !users.isEmpty()
            users.get(0).getId() == userEntity.getId()
            users.get(0).getName() == userEntity.getName()
            users.get(0).getSecondName() == userEntity.getSecondName()
            users.get(0).getLogin() == userEntity.getLogin()
            users.get(0).getEmail() == userEntity.getEmail()
            users.get(1).getId() == userEntity2.getId()
            users.get(1).getName() == userEntity2.getName()
            users.get(1).getSecondName() == userEntity2.getSecondName()
            users.get(1).getLogin() == userEntity2.getLogin()
            users.get(1).getEmail() == userEntity2.getEmail()

    }

    def "getAllUsers should return IllegalStateException"() {
        given:
            def userEntity = new UserEntity(
                    name: 'Konrad',
                    secondName: 'Nysa',
                    login: 'jkowalski',
                    password: '123qwe@Q',
                    confirmedPassword: '123qwe@Q',
                    email: 'jan.kowalski@poczta.pl',
                    roleType: RoleType.USER
            )
            def userEntity2 = new UserEntity(
                    name: 'Romek',
                    secondName: 'Domek',
                    login: 'rdomek',
                    password: '789qwe@Q',
                    confirmedPassword: '789qwe@Q',
                    email: 'romek.domek@poczta.pl',
                    roleType: RoleType.USER
            )
        and:
            userRepository.findAll() >> null
        when:
            def users = userService.getAllUsers()
        then:
            thrown(BusinessException)
    }

    def "Should update user with given Id"() {
        given:
            def userId = 1L
            def userDto = new UserDto(
                    name: 'Rafal   ',
                    secondName: 'Bachuta',
                    login: 'rbachuta',
                    email: 'rafal.bachuta@poczta.pl',
                    password: '456qwe@Q',
                    confirmedPassword: '456qwe@Q',
                    roleType: UserDto.RoleTypeEnum.ADMIN
            )
            def userEntity = new UserEntity(
                    name: 'Jan',
                    secondName: 'Kowalski',
                    login: 'jkowalski',
                    email: 'jan.kowalski@poczta.fm',
                    password: '123qwe@Q',
                    confirmedPassword: '123qwe@Q',
                    roleType: RoleType.USER
            )
        and:
            userRepository.findById(userId) >> userEntity
        when:
            userService.updateUser(userId, userDto)
        then:
            1 * userRepository.save(_ as UserEntity)
    }

    def "updateUser should throw IllegalStateException"() {
        given:
            def userId = 1L
            def userDto = new UserDto(
                    name: 'Rafal   ',
                    secondName: 'Bachuta',
                    login: 'rbachuta',
                    email: 'rafal.bachuta@poczta.pl',
                    password: '456qwe@Q',
                    confirmedPassword: '456qwe@Q',
                    roleType: UserDto.RoleTypeEnum.ADMIN
            )
        and:
            userRepository.findById(userId) >> null
        when:
            userService.updateUser(userId, userDto)
        then:
            thrown(BusinessException)
    }
}
