package pl.gov.coi.blox.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "USERS")
public class UserEntity extends AbstractEntity {


    @NotEmpty
    @Column(name = "LOGIN", nullable = false, length = 30, unique = true)
    private String login;
    @NotEmpty
    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;
    @NotEmpty
    @Column(name = "USERNAME", nullable = false, length = 30)
    private String username;
    @NotEmpty
    @Column(name = "EMAIL", nullable = false, length = 50,unique = true)
    private String email;

    @OneToMany
    Set<BlogEntity> blogs = new HashSet<>();

    @OneToMany
    Set<CommentEntity> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private Set<RoleEntity> roles = new HashSet<>();
}
