package com.educandoweb.course.resources; //subpacote resources que serão os recursos da aplicação

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<User>> findAll() { // o Tipo da resposta vai ser uma Lista de usuário
		List<User> list = service.findAll(); //Lista de usuário recebendo service.findAll()
		return ResponseEntity.ok().body(list);

	}
	
	@GetMapping(value = "/{id}") // http://localhost:8080/users/1 - aparece as informaçoes do usuário daquele id. no caso 1
	public ResponseEntity<User> findbyId(@PathVariable Long id){  // Método que vai buscar o usuário pelo id
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
