package pl.gov.coi.blox.entity;

import lombok.Getter;

@Getter
public enum RateType {
    VERY_GOOD(5),
    GOOD(4),
    SATISFACTORY(3),
    POOR(2),
    FAIL(1);

    private int value;

    RateType(int value) { this.value = value; }
}
