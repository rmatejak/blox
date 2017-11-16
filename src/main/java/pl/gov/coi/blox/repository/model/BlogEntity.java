package pl.gov.coi.blox.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table (name = "BLOG")
public class BlogEntity extends AbstractEntity{

    @Column (name = "AUTHOR", nullable = false, length = 30)
    private String author;
    @Column (name = "RATING", nullable = false)
    private int rating;
    @Column (name = "TYPE", nullable = false)
    private CategoryType type;
    @Column (name = "COMMENT")
    private String comment;

    @OneToMany
    Set<CategoryEntity> categories = new HashSet<>();

    @OneToMany
    Set<CommentEntity> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "BLOG_RATING",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "RATING"))
    private Set<RatingEntity> ratings = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "BLOG_AVAILABILITY",
            joinColumns = @JoinColumn(name = "ID_BLOG"),
            inverseJoinColumns = @JoinColumn(name = "ID_AVAILABILITY"))
    private Set<AvailabilityEntity> availabilities = new HashSet<>();

}
