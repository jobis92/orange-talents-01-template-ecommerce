package br.com.zup.ecommerce.usuarios.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotBlank
	@Email
	private String login;

	@NotBlank
	@Size(min = 6)
	private String senha;

	@NotNull
	private LocalDate dataCadastro;

	public Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha,
			@NotNull LocalDate dataCadastro) {
		Assert.isTrue(StringUtils.hasLength(login), "email não pode ser em branco");
		Assert.isTrue(StringUtils.hasLength(senha), "senha não pode ser em branco");
		Assert.isTrue(senha.length() >= 6, "senha precisa de no minimo 6 caracteres");

		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
		this.dataCadastro = dataCadastro;

	}

	@Override
	public String toString() {
		return "Usuario [Id=" + Id + ", login=" + login + ", senha=" + senha + ", dataCadastro=" + dataCadastro + "]";
	}

}
