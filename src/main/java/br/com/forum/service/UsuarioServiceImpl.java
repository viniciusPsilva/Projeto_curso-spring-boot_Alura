package br.com.forum.service;

import br.com.forum.Exception.UsuarioNotFoundException;
import br.com.forum.model.Usuario;
import br.com.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
