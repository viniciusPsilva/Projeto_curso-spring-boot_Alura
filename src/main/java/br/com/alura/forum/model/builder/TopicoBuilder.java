package br.com.alura.forum.model.builder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Resposta;
import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.model.Usuario;

public class TopicoBuilder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensagem;	
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NAO_RESPONDIDO;
	@ManyToOne
	private Usuario autor;
	@ManyToOne
	private Curso curso;
	@OneToMany(mappedBy = "topico")
	private List<Resposta> respostas = new ArrayList<>();

	public TopicoBuilder() {
			
	}

	public TopicoBuilder titulo(String titulo) {
		this.titulo = titulo;
		return this;
	}

	public TopicoBuilder mensagem(String mensagem) {
		this.mensagem = mensagem;
		return this;
	}

	public TopicoBuilder status(StatusTopico status) {
		this.status = status;
		return this;
	}

	public TopicoBuilder autor(Usuario autor) {
		this.autor = autor;
		return this;
	}

	public TopicoBuilder curso(Curso curso) {
		this.curso = curso;
		return this;
	}

	public TopicoBuilder addResposta(Resposta resposta) {
		this.respostas.add(resposta);
		return this;

	}

	public TopicoBuilder respostas(List<Resposta> respostas) {
		this.respostas = respostas;
		return this;
	}

	public Topico build() {
		Topico topico = new Topico();
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		topico.setStatus(this.status);
		topico.setAutor(this.autor);
		topico.setCurso(this.curso);
		topico.setRespostas(this.respostas);
		return topico;
	}
}
