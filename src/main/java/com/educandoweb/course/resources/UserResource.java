package com.educandoweb.course.resources; //subpacote resources que serão os recursos da aplicação

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<User>> findAll() { // o Tipo da resposta vai ser uma Lista de usuário
		List<User> list = service.findAll(); // Lista de usuário recebendo service.findAll()
		return ResponseEntity.ok().body(list);

	}

	@GetMapping(value = "/{id}") // http://localhost:8080/users/1 - aparece as informaçoes do usuário daquele id.
									// no caso 1
	public ResponseEntity<User> findbyId(@PathVariable Long id) { // Método que vai buscar o usuário pelo id
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/*
	 * Para que seja inserido um novo User, usa-se esses mapeamentos. Esse detalhe
	 * do uri em diante é que é para aparecer 201 no status do postman.
	 * Configurações http.
	 */

	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	/*
	 * Para deletar, returna Void, pois a resposta da requisição não retorna nenhum
	 * corpo(body()). Então para isso utiliza-se .noContent(), pois retorna uma resposta vazia. O corpo http para uma resposta vazia é 204.
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	//  Implementação básica da atualização de um usuário
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
		
	}

}
