package pl.gov.coi.blox.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Error {

    private final String code;
    private final String message;
}
