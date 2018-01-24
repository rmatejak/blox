package pl.gov.coi.blox.entity;

import lombok.Getter;
import lombok.Setter;
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
@Table(name = "CATEGORY")
public class CategoryEntity extends AbstractEntity {

    @OneToOne
    private BlogEntity owner;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCTRIPTION")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "AVAILABILITY")
    private StatusDto status;

    @OneToMany(mappedBy = "owner")
    private Set<ArticleEntity> articles = new HashSet<>();

    public void addArticle(ArticleEntity articleEntity){
        this.articles.add(articleEntity);
        articleEntity.setOwner(this);
    }
}
