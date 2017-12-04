package pl.gov.coi.blox.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ValidationMessage {
    NAME_SIZE_NOT_CORRECT(422, "Validation error! Name size is not correct!"),
    SECONDNAME_SIZE_NOT_CORRECT(422, "Validation error! Secondname size is not correct!"),
    LOGIN_SIZE_NOT_CORRECT(422, "Validation error! Login size is not correct!"),
    EMAIL_SIZE_NOT_CORRECT(422, "Validation error! Email size is not correct!"),
    EMAIL_MATCH_NOT_CORRECT(422, "Validation error! Email match is not correct!"),
    CONFIRMED_PASSWORD_IS_DIFFERENT(422,"Validiation error! Password and confirmed password are different!"),
    PASSWORD_REGEX(422, "Password contain at least 8 characters, 1 number, 1 lowercase character (a-z), 1 uppercase character (A-Z), 1 special character, contains only 0-9a-zA-Z. Example: P@ssW0rd"),
    ID_NOT_CORRECT(422, "Validation error! You should specify a number.");
    private final int code;
    private final String message;
}
