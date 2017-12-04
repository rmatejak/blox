package pl.gov.coi.blox.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityNotFoundMassage {

    USER_DOES_NOT_EXIST(400,"User doesn't exist!");

    private final int code;
    private final String message;
}
