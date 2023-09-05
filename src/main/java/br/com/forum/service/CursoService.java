package br.com.forum.service;

import br.com.forum.Exception.CursoNotFoundException;
import br.com.forum.model.Curso;

public interface CursoService {
	
	Curso buscarPorNome(String nomeCurso) throws CursoNotFoundException;
}
