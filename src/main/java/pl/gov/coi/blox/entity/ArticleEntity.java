package pl.gov.coi.blox.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.gov.coi.blox.api.model.RateTypeDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "ARTICLE")
public class ArticleEntity extends AbstractEntity {

    @OneToOne
    private CategoryEntity owner;
    @Column(name = "LOGIN", nullable = false, length = 30)
    private String author;
    @Column(name = "TOPIC", nullable = false, length = 150)
    private String topic;
    @Column(name = "CONTENT", length = 300)
    private String text;
    @Enumerated(EnumType.STRING)
    private RateType rateType;
    @Column(name = "RATING")
    private double rating;
    private double sumOfRates = 0;
    private int numberOfRating = 0;

    @OneToMany(mappedBy = "articleOwner")
    private Set<CommentEntity> comments = new HashSet<>();

    public void addComment(CommentEntity commentEntity){
        this.comments.add(commentEntity);
    }

    public void addRate(RateType rate){
        numberOfRating++;
        sumOfRates = (sumOfRates + rate.getValue());
        rating = (sumOfRates)/(numberOfRating);
    }
}
