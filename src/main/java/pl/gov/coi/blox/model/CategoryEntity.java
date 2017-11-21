package pl.gov.coi.blox.model;

import lombok.Getter;
import lombok.Setter;

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
@Table(name = "CATEGORY")
public class CategoryEntity extends AbstractEntity {

    private String name;
    private String desctription;
    private Boolean isActive;

    @OneToMany
    @JoinTable(
            name = "ARTICLE_CATEGORY",
            joinColumns = @JoinColumn(name = "ID_CATEGORY"),
            inverseJoinColumns = @JoinColumn(name = "ID_ARTICLE"))
    private Set<ArticleEntity> articles = new HashSet<>();
}
