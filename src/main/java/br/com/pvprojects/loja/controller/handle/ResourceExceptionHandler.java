package br.com.pvprojects.loja.controller.handle;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.pvprojects.loja.service.exceptions.HeaderNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(HeaderNotFoundException.class)
    public ResponseEntity<StandardError> headerNotFound(HeaderNotFoundException ex, HttpServletRequest request) {

        StandardError err = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Header_Not_Found",
                ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError err = new ValidationError(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value()
                , "Erro de validação", null, request.getRequestURI());
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

}
