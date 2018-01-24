package pl.gov.coi.blox.service;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
