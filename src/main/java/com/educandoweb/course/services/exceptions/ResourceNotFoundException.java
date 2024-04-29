package com.educandoweb.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/*
	 * O construtor vai receber um Object id, vai ser passado o id do objeto que não
	 * existe, nisso vai ser lançado a msg, mais o id que não foi encontrado
	 */

	public ResourceNotFoundException(Object id) {
		super("Resource Not Found. Id" + id);

	}

}
