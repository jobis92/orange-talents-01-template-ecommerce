package br.com.zup.ecommerce.usuarios.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ecommerce.usuarios.model.Usuario;
import br.com.zup.ecommerce.usuarios.request.NovoUsuarioRequest;

@RestController
public class UsuariosController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value="/usuarios")
	@Transactional
	public String cadastro(@RequestBody @Valid NovoUsuarioRequest request) {
		Usuario usuario = request.toModel();
		manager.persist(usuario);
		
		return usuario.toString();
	}

}
