package br.com.alura.forum.service;

import br.com.alura.forum.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    @Override
    public String gerarToken(Authentication authentication) {
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        Date dataAtual= new Date();
        Date dataExpiracao = new Date(dataAtual.getTime()+ Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API do forum da Alura") // aplicação responsavel por gerar o token
                .setSubject(usuarioLogado.getId().toString()) // informação referente ao usuário logado
                .setIssuedAt(dataAtual) // momento em que o token foi gerado
                .setExpiration(dataExpiracao) // momento em que o token ira expirar
                .signWith(SignatureAlgorithm.HS256, secret) // algoritimo que gerara o token e senha
                .compact();
    }
}
