package br.com.zup.ecommerce.usuarios.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zup.ecommerce.usuarios.model.Usuario;

public class NovoUsuarioRequest {

	@NotBlank
	@Email
	private String login;

	@NotBlank
	@Size(min = 6)
	private String senha;

	@NotNull
	private LocalDate dataCadastro;

	public NovoUsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha,
			@NotNull LocalDate dataCadastro) {
		
		this.login = login;
		this.senha = senha;
		this.dataCadastro = LocalDate.now();
	}

	public Usuario toModel() {

		return new Usuario(this.login, new BCryptPasswordEncoder().encode(senha), this.dataCadastro);
	}

}
