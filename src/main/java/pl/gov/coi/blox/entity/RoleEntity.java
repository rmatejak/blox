package pl.gov.coi.blox.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROLE", uniqueConstraints = @UniqueConstraint(name = "role_type_unique", columnNames = "roleType"))
@Getter
public class RoleEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private RoleEntity() {
    }

    public RoleEntity(RoleType roleType) {
        this.roleType = roleType;
    }
}
