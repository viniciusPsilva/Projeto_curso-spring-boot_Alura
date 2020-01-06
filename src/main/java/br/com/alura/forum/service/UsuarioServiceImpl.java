package br.com.alura.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.forum.Exception.UsuarioNotFoundException;
import br.com.alura.forum.model.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	public UsuarioRepository usuarioRepository;

	@Override
	public Usuario buscarPorNome(String nome) throws UsuarioNotFoundException {
		
		return usuarioRepository.findByNome(nome)
				.orElseThrow(() ->  new UsuarioNotFoundException("nenhum usuario encontrado com o nome: "+ nome));
	}

}
