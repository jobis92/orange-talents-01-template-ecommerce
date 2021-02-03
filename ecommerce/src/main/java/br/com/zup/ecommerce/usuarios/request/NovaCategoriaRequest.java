package br.com.zup.ecommerce.usuarios.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zup.ecommerce.usuarios.model.Categoria;
import br.com.zup.ecommerce.validacao.UniqueValue;

public class NovaCategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Categoria já cadastrada!")
	private String nome;

	@Positive
	private Long idCategoriaMae;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria toModel(EntityManager manager) {
		Categoria categoria = new Categoria(nome);
		if (idCategoriaMae != null) {
			Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
			Assert.notNull(categoriaMae, "Id inexistente!");
			categoria.setMae(categoriaMae);
		}
		return categoria;
	}

}
