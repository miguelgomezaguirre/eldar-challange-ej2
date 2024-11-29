package com.eldar.challange.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<?> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        return new ResponseEntity<>(createErrorMessage(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TarjetaNotFoundException.class)
    public ResponseEntity<?> handleTarjetaNotFoundException(TarjetaNotFoundException ex) {
        return new ResponseEntity<>(createErrorMessage(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TarjetaVencidaException.class)
    public ResponseEntity<?> handleTarjetaVencidaException(TarjetaVencidaException ex) {
        return new ResponseEntity<>(createErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MarcaNoValidaException.class)
    public ResponseEntity<?> handleMarcaNoValidaException(MarcaNoValidaException ex) {
        return new ResponseEntity<>(createErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CvvIncorrectoException.class)
    public ResponseEntity<?> handleCvvIncorrectoException(CvvIncorrectoException ex) {
        return new ResponseEntity<>(createErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotValidException.class)
    public ResponseEntity<?> handleEmailNotValidException(EmailNotValidException ex) {
        return new ResponseEntity<>(createErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> createErrorMessage(String message) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", message);
        return errorMap;
    }
}
