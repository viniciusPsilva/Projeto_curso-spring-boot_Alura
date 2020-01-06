package br.com.alura.forum.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.alura.forum.Exception.UsuarioNotFoundException;
import br.com.alura.forum.model.DTO.ExceptionErrorDTO;

@ControllerAdvice
public class UsuarioExceptionHandler {

	@ExceptionHandler(value = UsuarioNotFoundException.class)
	public ResponseEntity<ExceptionErrorDTO> handlerConflict(Exception ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionErrorDTO(ex.getMessage()));
	}

}
