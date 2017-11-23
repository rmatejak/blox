package pl.gov.coi.blox.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class UserEntity extends AbstractEntity {

    @Column(name = "IMIE", nullable = false, length = 30)
    private String name;
    @Column(name = "NAZWISKO", nullable = false, length = 30)
    private String secondname;
    @Column(name = "LOGIN", nullable = false, length = 30, unique = true)
    private String login;
    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;
    @Column(name = "EMAIL", nullable = false, length = 50, unique = true)
    private String email;
    @Column (name = "ROLA", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToMany
    @JoinTable(
            name = "USER_BLOG",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_BLOG"))
    private Set<BlogEntity> blogs = new HashSet<>();
    @OneToMany
    @JoinTable(
            name = "USER_COMMENT",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_COMMENT"))
    private Set<CommentEntity> comments = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private Set<RoleEntity> roles = new HashSet<>();

    public void addBlog(BlogEntity blogEntity) {
        this.blogs.add(blogEntity);
    }
}
