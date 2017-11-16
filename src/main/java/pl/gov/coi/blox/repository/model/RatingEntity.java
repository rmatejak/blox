package pl.gov.coi.blox.repository.model;

//klasa odpowiadająca za ocene blogów

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table (name = "RATING", uniqueConstraints =  @UniqueConstraint(name = "rating_unique", columnNames = "Rating"))
public class RatingEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @NotEmpty
    private Rating rating;

    public RatingEntity(){}

    public RatingEntity(Rating rating) {
        this.rating = rating;
    }
}