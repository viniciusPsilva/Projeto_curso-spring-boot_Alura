package br.com.forum.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationExceptionHandler {
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<?> handler() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
