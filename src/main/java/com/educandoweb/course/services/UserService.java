package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

/*
 * Component registration = Quando um objeto vai ser injetado a classe desse objeto 
 * tem que ta registrada no mecanismo de injeção de dependencia.
 * Exemplo: a classe UserResource DEPENDE de UserService, 
 * UserService vai ser injetado automaticamente por causa do @Autowired, mas para que funcione,
 * a CLASSE UserService precisa ta cadastrada como componente. 
 * Utilizou-se a Annotation @Service, pois é uma camada de serviço.
 * A classe UserService DEPENDE de UserRepository, mas a CLASSE UserRepository não vai precisar 
 * pois ela já está HERDANDO jpaRepository, um componente do spring...
 */

@Service
public class UserService {

	/*
	 * Vai ser implementado duas operações 1) para buscar todos os usuários e 2)
	 * buscar os usuários por id. Para isso, o UserService vai ter que ter uma
	 * dependencia para userRepository
	 */
	@Autowired
	private UserRepository repository;

	public List<User> findAll() { // // Método para retornar todos os usuários do banco de dados
		return repository.findAll();
	}

	public User findById(Long id) { // Método para retornar um usuário por id
		Optional<User> obj = repository.findById(id);
		return obj.get(); // vai retornar o valor do id
	}

	/*
	 * Operação básica para inserir no banco de dados um novo objeto do tipo User
	 */
	public User insert(User obj) {
		return repository.save(obj);
	}

}
