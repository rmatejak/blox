package pl.gov.coi.blox.model;

//klasa odpowiadaja za dostępność przeglądania blogów i kont

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table (name = "AVAILABILITY", uniqueConstraints = @UniqueConstraint(name = "availability_unique", columnNames = "Availability"))
public class AvailabilityEntity extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    @NotEmpty
    private Availability availability;

    public AvailabilityEntity(){}

    public AvailabilityEntity(Availability availability) {
        this.availability = availability;
    }
}
