package br.com.alura.forum.service;

import br.com.alura.forum.Exception.InvalidTokenException;
import org.springframework.security.core.Authentication;

public interface TokenService {

    String gerarToken(Authentication authentication);

    boolean isValid(String token);

    Long getIdUsuario(String token);
}
