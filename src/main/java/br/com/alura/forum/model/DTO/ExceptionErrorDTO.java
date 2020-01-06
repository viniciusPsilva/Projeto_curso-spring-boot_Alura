package br.com.alura.forum.model.DTO;

public class ExceptionErrorDTO {
	public String mensagem;

	public ExceptionErrorDTO(String message) {
		this.mensagem = message;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
