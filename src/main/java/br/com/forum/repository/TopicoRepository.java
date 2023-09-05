package br.com.forum.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.com.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	
	List<Topico> findByTitulo(String titulo);
	
	List<Topico> findByCurso_Nome(String nomeCurso);	
	
	List<Topico> findByDataCriacao(LocalDateTime data);
	
	List<Topico> findByAutor_Nome(String nomeAutor);
}
