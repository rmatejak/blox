package pl.gov.coi.blox.model;

import lombok.Getter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@Entity
@Table(name = "ROLE", uniqueConstraints =  @UniqueConstraint(name = "role_type_unique", columnNames = "roleType"))
@Getter
public class RoleEntity {

    @Id
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private RoleType roleType;

    private RoleEntity() {
    }

    public RoleEntity(RoleType roleType) {
        this.roleType = roleType;
    }
}
