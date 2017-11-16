package pl.gov.coi.blox.repository.model;

//klasa odpowiadająca za komentarze dodawane pod blogiem i artykułem

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table (name = "COMMENT")
public class CommentEntity extends AbstractEntity {

     @Column (name = "CONTENT",length = 300)
     private String text;

}