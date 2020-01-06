package br.com.alura.forum.model.DTO;

public class ValidationErrorDTO {
    private String field;
    private String message;

    public ValidationErrorDTO(String field, String message){
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}

