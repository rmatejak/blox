package pl.gov.coi.blox.entity;

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
    private String secondName;
    @Column(name = "LOGIN", nullable = false, length = 30, unique = true)
    private String login;
    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;
    @Column(name = "CONFIRMED_PASSWORD", nullable = false, length = 30)
    private String confirmedPassword;
    @Column(name = "EMAIL", nullable = false, length = 50, unique = true)
    private String email;
    @Column (name = "ROLA", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToMany(mappedBy = "owner")
    private Set<BlogEntity> blogs = new HashSet<>();
    @OneToMany(mappedBy = "userOwner")
    private Set<CommentEntity> comments = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private Set<RoleEntity> roles = new HashSet<>();

    public void addBlog(BlogEntity blogEntity) {
        this.blogs.add(blogEntity);
        blogEntity.setOwner(this);
    }

    public void addComment(CommentEntity commentEntity){
        this.comments.add(commentEntity);
        commentEntity.setUserOwner(this);
    }

    public void removeBlog(BlogEntity blogEntity) {
        this.blogs.remove(blogEntity);
        blogEntity.setOwner(null);
    }
}
