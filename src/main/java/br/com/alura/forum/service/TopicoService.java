package br.com.alura.forum.service;

import java.util.List;

import br.com.alura.forum.Exception.CursoNotFoundException;
import br.com.alura.forum.Exception.TopicoNotFoundException;
import br.com.alura.forum.Exception.UsuarioNotFoundException;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.model.form.AtualizacaoTopicoForm;
import br.com.alura.forum.model.form.TopicoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicoService {

	public List<Topico> listar();

	public List<Topico> buscarPorCurso(String nomeCurso);
	
	public List<Topico> buscarTitulo(String titulo);

	public Topico cadastrar(TopicoForm topico) throws CursoNotFoundException, UsuarioNotFoundException;

	Topico atualizar(AtualizacaoTopicoForm topicoForm, Long id) throws TopicoNotFoundException;

	public Topico buscarPorId(Long id) throws TopicoNotFoundException;

    void deletar(Long id) throws TopicoNotFoundException;

    Page<Topico> buscarTopicosPaginados(Pageable pageable);
}
