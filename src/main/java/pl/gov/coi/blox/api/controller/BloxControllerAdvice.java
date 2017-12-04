package pl.gov.coi.blox.api.controller;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.gov.coi.blox.api.Error;

@ControllerAdvice
public class BloxControllerAdvice {

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public Error handleBadRequestException(HttpMessageNotReadableException ex) {
        System.out.println(ex);
        return new Error("404", "Zły format zapytania.");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Error handleNullException (MethodArgumentNotValidException exc){
        System.out.println(exc);
        return new Error("303", "Wrong data");
    }
}
