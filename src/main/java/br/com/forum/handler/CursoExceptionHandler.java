package br.com.forum.handler;

import br.com.forum.Exception.CursoNotFoundException;
import br.com.forum.model.DTO.ExceptionErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CursoExceptionHandler {

	@ExceptionHandler(value = CursoNotFoundException.class)
	public ResponseEntity<ExceptionErrorDTO> handlerConflict(Exception ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionErrorDTO(ex.getMessage()));
	}	
}
