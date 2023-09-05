package br.com.forum.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String gerarToken(Authentication authentication);

    boolean isValid(String token);

    Long getIdUsuario(String token);
}
