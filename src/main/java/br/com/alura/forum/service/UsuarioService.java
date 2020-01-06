package br.com.alura.forum.service;

import br.com.alura.forum.Exception.UsuarioNotFoundException;
import br.com.alura.forum.model.Usuario;

public interface UsuarioService {

	Usuario buscarPorNome(String nome) throws UsuarioNotFoundException;
}
