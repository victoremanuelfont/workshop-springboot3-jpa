package com.educandoweb.course.resources; //subpacote resources que serão os recursos da aplicação

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;

@RestController // Annotetion para indicar que é um recurso web implementado por um controlador
				// rest
@RequestMapping(value = "/users") // Precisa nomer o recurso, que nesse caso é associado a entidade "User"
public class UserResource { // Correspondente web a entidade user

	/*
	 * Método endpoint para acessar/retornar os usuários: ResponseEntity = Tipo de
	 * retorno específico para retornar respostas de requisições web
	 */
	@GetMapping
	public ResponseEntity<User> findAll() {
		User u = new User(1L, "Maria", "maria@gmail.com", "999999", "12345");
		return ResponseEntity.ok().body(u);
		/*
		 * .ok() = para retornar a resposta com sucesso .body(u) = para enviar o corpo
		 * da resposta do usuário u que foi instanciado anteriormente
		 */
	}
	/*
	 * Após executar, aparece no endereço localhost:8080/users a resposta do método
	 * anterior
	 */

}
