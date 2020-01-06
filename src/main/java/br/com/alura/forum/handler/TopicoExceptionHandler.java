package br.com.alura.forum.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.alura.forum.Exception.TopicoNotFoundException;
import br.com.alura.forum.model.DTO.ExceptionErrorDTO;

@ControllerAdvice
public class TopicoExceptionHandler {

	@ExceptionHandler(value = { TopicoNotFoundException.class })
	protected ResponseEntity<ExceptionErrorDTO> handleConflict(Exception ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionErrorDTO(ex.getMessage()));
	}
}
