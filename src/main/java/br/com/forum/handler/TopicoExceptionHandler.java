package br.com.forum.handler;

import br.com.forum.Exception.TopicoNotFoundException;
import br.com.forum.model.DTO.ExceptionErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TopicoExceptionHandler {

	@ExceptionHandler(value = { TopicoNotFoundException.class })
	protected ResponseEntity<ExceptionErrorDTO> handleConflict(Exception ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionErrorDTO(ex.getMessage()));
	}
}
