package pl.gov.coi.blox.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "BLOG")
public class BlogEntity extends AbstractEntity {

    @Enumerated (EnumType.STRING)
    @Column(name = "RATE")
    private RateType rateType;
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private BlogType blogType;
    @Column(name = "CONTENT")
    private String description;
    @Column(name = "AVAILABILITY")
    private boolean active;

    @OneToMany
    @JoinTable(
            name = "USER_CATEGORY",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_CATEGORY"))
    private Set<CategoryEntity> categories = new HashSet<>();

    @OneToMany
    @JoinTable(
            name = "USER_COMMENT",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_COMMENT"))
    private Set<CommentEntity> comments = new HashSet<>();
}
