package pl.gov.coi.blox.model;

public enum RateType {
    VERY_GOOD(5),
    GOOD(4),
    SATISFACTORY(3),
    POOR(2),
    FAIL(1);

    private final int value;

    RateType(int value) {
    this.value = value;
    }
}
