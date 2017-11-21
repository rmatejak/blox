package pl.gov.coi.blox.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "ARTICLE")
public class ArticleEntity extends AbstractEntity {

    @Column(name = "LOGIN", nullable = false, length = 30)
    private String author;
    @Column(name = "TOPIC", nullable = false, length = 150)
    private String topic;
    @Column(name = "CONTENT", length = 300)
    private String text;
    @Column(name = "RATING")
    private int rating;
    @OneToMany
    @JoinTable(
            name = "ARTICLE_COMMENT",
            joinColumns = @JoinColumn(name = "ID_ARTICLE"),
            inverseJoinColumns = @JoinColumn(name = "ID_COMMENT"))
    private Set<CommentEntity> comments = new HashSet<>();
}
