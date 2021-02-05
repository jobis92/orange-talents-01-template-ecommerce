package br.com.zup.ecommerce.security;

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

import br.com.zup.ecommerce.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Configurações de Autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// Configurações de Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/produtos/{id:[0-9]+}").permitAll()
				.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
				.antMatchers(HttpMethod.POST, "/categorias").permitAll()
				.antMatchers(HttpMethod.POST, "/auth").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
				.and()
					.cors()
				.and()
					.csrf().disable()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().headers().frameOptions().sameOrigin() 
				.and()
					.addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository),
						UsernamePasswordAuthenticationFilter.class);

	}

	// Configurações de recursoso estaticos(js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**");

	}
}
