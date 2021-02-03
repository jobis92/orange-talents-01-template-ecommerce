package br.com.zup.ecommerce.usuarios.request;

import java.time.LocalDate;

import org.junit.Test;

import br.com.zup.ecommerce.usuarios.model.Usuario;

public class NovoUsuarioRequestTest {
	
	@Test
	public void naoDeveriaCadastrarUsuarioComMesmoEmail() {
		Usuario usuario1 = new Usuario("email@email.com","123456",LocalDate.now());
		
	}

}
