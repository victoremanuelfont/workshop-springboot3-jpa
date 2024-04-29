package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

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
		/*
		 * Trocou-se .get por .orElseThrow((, pois o .get da o erro 500. .orElseThrow((
		 * = funciona assim: ele vai tentar dar o get, se não tiver User, vai lançar a
		 * exceção.
		 */
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));

	}

	/*
	 * Operação básica para inserir no banco de dados um novo objeto do tipo User
	 */
	public User insert(User obj) {
		return repository.save(obj);
	}

	// Vai ser passado o id do usuário e vai ser deletado o usuário daquele id
	public void delete(Long id) {
		repository.deleteById(id);

	}

	/*
	 * getReferenceById = instancia o user mas não vai no banco de dados. updateData
	 * = Atualiza os dados do entity com base nos dados que chegaram no obj
	 */
	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);

	}

	private void updateData(User entity, User obj) {

		/*
		 * Atualiza os valores de cada atributo. Nem todos os campos foram atualizados,
		 * por questões "Regras".
		 */
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setEmail(obj.getPhone());

	}

}
