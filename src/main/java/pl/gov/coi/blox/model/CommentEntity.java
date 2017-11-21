package pl.gov.coi.blox.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "COMMENT")
public class CommentEntity extends AbstractEntity {

    @Column(name = "CONTENT", length = 300)
    private String content;
    @OneToOne
    @JoinColumn (name = "ID_USER")
    private UserEntity userEntity;
}
