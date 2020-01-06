package br.com.alura.forum.config;

import br.com.alura.forum.Exception.InvalidTokenException;
import br.com.alura.forum.Exception.UsuarioNotFoundException;
import br.com.alura.forum.model.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import br.com.alura.forum.service.TokenService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoFilterConfiguration extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoFilterConfiguration(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);

        boolean tokenIsValid = tokenService.isValid(token);

        if (tokenIsValid){
            autenticarUsuario(token);
        }

        //libera a request para seguir seu fluxo após rodar a lógica do filtro
        filterChain.doFilter(request, response);
    }

    private void autenticarUsuario(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        //Seta no contexto do Spring o usuario autenticado para a requisição atual
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
