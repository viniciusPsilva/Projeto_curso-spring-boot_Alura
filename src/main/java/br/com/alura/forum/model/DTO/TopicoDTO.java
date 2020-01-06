package br.com.alura.forum.model.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.Topico;
import org.springframework.data.domain.Page;

public class TopicoDTO {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;

	public TopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}

    public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public static List<TopicoDTO> converter(List<Topico> topicos) {

		return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
	}

	public static Page<TopicoDTO> converter(Page<Topico> paginaDeTopicos) {
		return paginaDeTopicos.map(TopicoDTO::new);
	}

}
