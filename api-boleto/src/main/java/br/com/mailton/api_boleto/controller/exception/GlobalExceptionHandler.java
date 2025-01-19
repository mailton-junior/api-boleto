package br.com.mailton.api_boleto.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> applicationException(ApplicationException e, WebRequest request) {
        ErroResponse response = ErroResponse.builder()
                .erro(e.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> applicationException(MethodArgumentNotValidException e, WebRequest request) {
        String errors = e.getFieldErrors().stream().map(item -> item.getField() + " " + item.getDefaultMessage())
                .collect(Collectors.joining());

        ErroResponse response = ErroResponse.builder()
                .erro(errors)
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
