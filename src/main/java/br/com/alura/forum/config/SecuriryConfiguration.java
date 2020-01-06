package br.com.alura.forum.config;

import br.com.alura.forum.repository.UsuarioRepository;
import br.com.alura.forum.service.AutenticacaoService;
import br.com.alura.forum.service.TokenService;
import br.com.alura.forum.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecuriryConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenService tokenService;
@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    //Cria um Bean de authentication manager para injetar no AutenticationController
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //lida com a confiração de autenticação(login)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService) // configura qual é o service responsável pela logica de autenticação
                .passwordEncoder(new BCryptPasswordEncoder()); // configura um algoritimo de criptografia para a senha do usuário.
    }

    //lida com a configuração de liberação de acesso aos end-point's, configura os perfis de acesso
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topico").permitAll()
                .antMatchers(HttpMethod.GET, "/topico/*").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable() // desabilita a tratativa contra csrf(não é necessário tratar pois estamos usando token para se autenticar// )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //configura a sessão para se compirtar como Stateless(sem contrle de sessão no lado do servidor)
                .and().addFilterBefore(new AutenticacaoFilterConfiguration(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class); // registra o filtro responsavel por validar o token do usuário


    }

    //lida com a configuracao de liberação de recursos estaticos(css, js, imagens ...)
    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}
