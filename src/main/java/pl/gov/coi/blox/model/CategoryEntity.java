package pl.gov.coi.blox.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table (name = "CATEGORY", uniqueConstraints =  @UniqueConstraint(name = "category_type_unique", columnNames = "CategoryType"))
public class CategoryEntity extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    @NotEmpty
    private CategoryType categoryType;

    private CategoryEntity(){}

    public CategoryEntity(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    @OneToMany
    Set<ArticleEntity> articles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "CATEGORY_AVAILABILITY",
            joinColumns = @JoinColumn(name = "ID_CATEGORY"),
            inverseJoinColumns = @JoinColumn(name = "ID_AVAILABILITY"))
    private Set<AvailabilityEntity> availabilities = new HashSet<>();
}
