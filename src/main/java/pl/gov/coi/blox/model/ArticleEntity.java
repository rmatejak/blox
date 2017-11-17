package pl.gov.coi.blox.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "ARTICLE")
public class ArticleEntity  extends AbstractEntity{


    @Column (name = "LOGIN", nullable = false, length = 30)
    private String author;
    @Column (name = "TOPIC", nullable = false, length = 150)
    private String topic;
    @Column (name = "DATE_OF_CREATION", nullable = false, length = 30)
    private String dateOfCreation;
    @Column (name = "CONTENT",length = 300)
    private String text;
    @Column (name = "RATING")
    private int rating;

    @OneToMany
    private Set<CommentEntity> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "ARTICLE_AVAILABILITY",
            joinColumns = @JoinColumn(name = "ID_ARTICLE"),
            inverseJoinColumns = @JoinColumn(name = "ID_AVAILABILITY"))
    private Set<AvailabilityEntity> availabilities = new HashSet<>();
}
