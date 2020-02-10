package io.welfareteam.api.config;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.welfareteam.api.resource.ErrorModel;
import io.welfareteam.api.resource.ErrorsModel;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { NoSuchElementException.class})
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		
		ErrorsModel errors = new ErrorsModel();
		ErrorModel error = new ErrorModel();
		error.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
		error.setMessage(ex.getMessage());
		errors.setErrors(Arrays.asList(error));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	
	@ExceptionHandler(value = { NotFound.class, BadRequest.class})
	protected ResponseEntity<Object> handleNotFound(HttpClientErrorException ex, WebRequest request) {
		
		ErrorsModel errors = new ErrorsModel();
		ErrorModel error = new ErrorModel();
		error.setCode(ex.getStatusText());
		error.setMessage(ex.getMessage());
		errors.setErrors(Arrays.asList(error));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), ex.getStatusCode(), request);
	}
	
}