package br.com.forum.service;

import br.com.forum.Exception.UsuarioNotFoundException;
import br.com.forum.model.Usuario;

public interface UsuarioService {

	Usuario buscarPorNome(String nome) throws UsuarioNotFoundException;
}
