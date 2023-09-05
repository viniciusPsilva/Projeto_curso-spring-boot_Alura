package br.com.forum.service;

import br.com.forum.Exception.CursoNotFoundException;
import br.com.forum.model.Curso;
import br.com.forum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService{

	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public Curso buscarPorNome(String nomeCurso) throws CursoNotFoundException {
		return cursoRepository.findByNome(nomeCurso).orElseThrow(() -> new CursoNotFoundException("Não foi encontrado o curso com o nome: "+nomeCurso));
	}

}
