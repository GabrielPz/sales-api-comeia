package com.gabrielhenrique.salesapicomeia.exceptions;

import java.util.UUID;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        // Check if the cause is a ConversionFailedException and involves UUID
        if (ex.getCause() instanceof ConversionFailedException) {
            ConversionFailedException cfe = (ConversionFailedException) ex.getCause();
            if (UUID.class.equals(cfe.getTargetType().getType())) {
                return new ResponseEntity<>("Invalid UUID format: " + ex.getValue(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Invalid request: " + ex.getName(), HttpStatus.BAD_REQUEST);
    }
}
