package pl.gov.coi.blox.entity;

import lombok.Getter;
import lombok.Setter;
import pl.gov.coi.blox.model.BlogType;
import pl.gov.coi.blox.model.RateType;

@Getter
@Setter
public class BlogViewDto {
    private RateType rateType;
    private BlogType blogType;
    private String description;
    private boolean active;
}
