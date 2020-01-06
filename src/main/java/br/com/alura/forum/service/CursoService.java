package br.com.alura.forum.service;

import br.com.alura.forum.Exception.CursoNotFoundException;
import br.com.alura.forum.model.Curso;

public interface CursoService {
	
	Curso buscarPorNome(String nomeCurso) throws CursoNotFoundException;
}
