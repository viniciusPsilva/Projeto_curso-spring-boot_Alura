package br.com.forum.handler;

import br.com.forum.model.DTO.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorExceptionHandler {

    //usado para internacionalizar as mensagem de erro de acordo com o idioma do servidor
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<ValidationErrorDTO>> handlerConflict(MethodArgumentNotValidException ex){
        List<ValidationErrorDTO> listErrorsDTO = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors.forEach( e -> {

            //monta a mensagem de erro lançada de acordo com uma localização
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidationErrorDTO errorDTO = new ValidationErrorDTO(e.getField(), message);
            listErrorsDTO.add(errorDTO);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listErrorsDTO);
    }
}

