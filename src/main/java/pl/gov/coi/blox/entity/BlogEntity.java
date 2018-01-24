package pl.gov.coi.blox.entity;

import lombok.Getter;
import lombok.Setter;
import pl.gov.coi.blox.api.model.BlogType;
import pl.gov.coi.blox.api.model.RateDto;
import pl.gov.coi.blox.api.model.RateTypeDto;
import pl.gov.coi.blox.api.model.StatusDto;

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
@Table(name = "BLOG")
public class BlogEntity extends AbstractEntity {

    @OneToOne
    private UserEntity owner;
    @Enumerated (EnumType.STRING)
    private RateType rateType;
    @Column(name = "RATE")
    private double rating;
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private BlogType blogType;
    @Column(name = "CONTENT")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "AVAILABILITY",nullable = false)
    private StatusDto status;
    private double sumOfRates = 0;
    private int numberOfRating = 0;

    @OneToMany(mappedBy = "owner")
    private Set<CategoryEntity> categories = new HashSet<>();

    @OneToMany(mappedBy = "blogOwner")
    private Set<CommentEntity> comments = new HashSet<>();

    public void addCategory(CategoryEntity categoryEntity){
        this.categories.add(categoryEntity);
        categoryEntity.setOwner(this);
    }

    public void addComment(CommentEntity commentEntity){
        this.comments.add(commentEntity);
        commentEntity.setBlogOwner(this);
    }

    public void addRate(RateType rate){
        numberOfRating++;
        sumOfRates = (sumOfRates + rate.getValue());
        rating = (sumOfRates)/ numberOfRating;
    }
}
