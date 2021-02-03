package br.com.zup.ecommerce.usuarios.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zup.ecommerce.usuarios.model.Usuario;
import br.com.zup.ecommerce.validacao.UniqueValue;

public class NovoUsuarioRequest {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "email já cadastrado!")
	private String email;

	@NotBlank
	@Size(min = 6, max = 50)
	private String senha;

	@NotNull
	private LocalDate dataCadastro;

	public NovoUsuarioRequest(@NotBlank @Email String email, @NotBlank @Size(min = 6) String senha,
			@NotNull LocalDate dataCadastro) {

		this.email = email;
		this.senha = senha;
		this.dataCadastro = LocalDate.now();
	}

	public Usuario toModel() {

		return new Usuario(this.email, new BCryptPasswordEncoder().encode(senha), this.dataCadastro);
	}

}
