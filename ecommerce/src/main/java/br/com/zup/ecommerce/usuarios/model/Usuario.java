package br.com.zup.ecommerce.usuarios.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 6)
	private String senha;

	@NotNull
	private LocalDate dataCadastro;

	@Deprecated
	public Usuario() {

	}

	public Usuario(@NotBlank @Email String email, @NotBlank @Size(min = 6) String senha,
			@NotNull LocalDate dataCadastro) {
		Assert.isTrue(StringUtils.hasLength(email), "email não pode ser em branco");
		Assert.isTrue(StringUtils.hasLength(senha), "senha não pode ser em branco");
		Assert.isTrue(senha.length() >= 6, "senha precisa de no minimo 6 caracteres");

		this.email = email;
		this.senha = senha;
		this.dataCadastro = dataCadastro;

	}

	@Override
	public String toString() {
		return "Usuario [Id=" + Id + ", email=" + email + ", senha=" + senha + ", dataCadastro=" + dataCadastro + "]";
	}

	public Long getId() {
		return Id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
