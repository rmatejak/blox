package pl.gov.coi.blox.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "COMMENT")
public class CommentEntity extends AbstractEntity {

    @OneToOne
    private UserEntity userOwner;
    @OneToOne
    private BlogEntity blogOwner;
    @OneToOne
    private ArticleEntity articleOwner;
    @Column(name = "CONTENT", length = 300)
    private String content;

}
