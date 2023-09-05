package br.com.forum.repository;

import java.util.Optional;

import br.com.forum.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long>{
	public Optional<Curso> findByNome(String nomeCurso);
}
