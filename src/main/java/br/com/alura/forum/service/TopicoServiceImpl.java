package br.com.alura.forum.service;

import java.util.List;
import java.util.Optional;

import br.com.alura.forum.model.form.AtualizacaoTopicoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.forum.Exception.CursoNotFoundException;
import br.com.alura.forum.Exception.TopicoNotFoundException;
import br.com.alura.forum.Exception.UsuarioNotFoundException;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.model.builder.TopicoBuilder;
import br.com.alura.forum.model.form.TopicoForm;
import br.com.alura.forum.repository.TopicoRepository;

@Service
public class TopicoServiceImpl implements TopicoService {
	@Autowired
	private TopicoRepository repository;

	@Autowired
	private CursoService cursoService;

	@Autowired
	private UsuarioService usuarioService;

	private final String TOPICO_NOT_FOUND_EXCEPTION_MSG = "Não Foi possivel Encontrar um topico com o Id :";

	@Override
	public List<Topico> listar() {
		return repository.findAll();
	}

	@Override
	public List<Topico> buscarPorCurso(String nomeCurso) {
		return repository.findByCurso_Nome(nomeCurso);
	}

	@Override
	public List<Topico> buscarTitulo(String titulo) {
		return repository.findByTitulo(titulo);
	}

	@CacheEvict(value = "ListaDeTopicos", allEntries = true) //informa ao spring para invalidar o espaço de memoria em cache reservado para "listaDeTopicos" sempre que o metodo cadastrar for executado,
	@Override
	public Topico cadastrar(TopicoForm topicoForm) throws CursoNotFoundException, UsuarioNotFoundException {

		Topico topico = new TopicoBuilder()
				.titulo(topicoForm.getTitulo())
				.mensagem(topicoForm.getMensagem())
				.curso(cursoService.buscarPorNome(topicoForm.getNomeCurso()))
				.autor(usuarioService.buscarPorNome("vinicius")).build();

		return repository.save(topico);
	}

	@CacheEvict(value = "ListaDeTopicos", allEntries = true) //informa ao spring para invalidar o espaço de memoria em cache reservado para "listaDeTopicos" sempre que o metodo cadastrar for executado,
	@Override
	public Topico atualizar(AtualizacaoTopicoForm topicoForm, Long id) throws TopicoNotFoundException{

		Topico topico = buscarPorId(id);
		topico.setTitulo(topicoForm.getTitulo());
		topico.setMensagem(topicoForm.getMensagem());

		return topico;
	}

	@Override
	public Topico buscarPorId(Long id) throws TopicoNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new TopicoNotFoundException(TOPICO_NOT_FOUND_EXCEPTION_MSG + id));
	}

	@CacheEvict(value = "ListaDeTopicos", allEntries = true) //informa ao spring para invalidar o espaço de memoria em cache reservado para "listaDeTopicos" sempre que o metodo cadastrar for executado,
	@Override
	public void deletar(Long id) throws TopicoNotFoundException {
		repository.delete(buscarPorId(id));
	}

	@Cacheable(value = "ListaDeTopicos") //indica ao spring cache que o retorno do metodo deve ser armazenado em cache, da um nome para os armazenados com o parametro value
	@Override
	public Page<Topico> buscarTopicosPaginados(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
