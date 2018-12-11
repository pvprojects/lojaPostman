package br.com.pvprojects.loja.infra.handle;

import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_REQUISICAO;
import static br.com.pvprojects.loja.util.ConventionsHelper.ERRO_VALIDACAO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.pvprojects.loja.infra.handle.exceptions.BadRequestException;
import br.com.pvprojects.loja.infra.handle.exceptions.DefaultException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<StandardError> defaultException(DefaultException ex) {

        StandardError err = new StandardError(ERRO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequest(BadRequestException ex, HttpServletRequest request) {

        StandardError err = new StandardError(ERRO_REQUISICAO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError err = new ValidationError(ERRO_VALIDACAO, null);

        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

}