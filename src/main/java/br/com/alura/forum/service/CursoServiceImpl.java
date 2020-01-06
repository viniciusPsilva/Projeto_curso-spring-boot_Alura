package br.com.alura.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.forum.Exception.CursoNotFoundException;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService{

	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public Curso buscarPorNome(String nomeCurso) throws CursoNotFoundException {
		return cursoRepository.findByNome(nomeCurso).orElseThrow(() -> new CursoNotFoundException("Não foi encontrado o curso com o nome: "+nomeCurso));
	}

}
