package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/*
 * ControllerAvice vai interceptar possiveis erros, para que sejam tratados aqui
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	/*
	 * Essa anotation significa que o metodo resourceNotFound vai interceptar as
	 * exceções do tipo ResourceNotFoundException, e vai fazer o tratamento que
	 * tiver dentro do método.
	 * 
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);		
	}

}
