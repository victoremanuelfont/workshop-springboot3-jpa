package com.educandoweb.course.repositories; //Pacotes com Repositories com operações que serão feitas em cada classe correspondente

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	/*
	 * JpaRepository é interface, então UserRepository tb vai ser.
	 * Vai instanciar o objeto repository.
	 */

}
