package br.com.forum.model.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoTopicoForm {

    @Length(min = 3, max = 100)
    @NotNull
    @NotEmpty
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(min = 3 , max = 500)
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
