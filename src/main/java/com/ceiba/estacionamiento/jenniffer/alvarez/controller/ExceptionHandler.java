package com.ceiba.estacionamiento.jenniffer.alvarez.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
 
  
	@org.springframework.web.bind.annotation.ExceptionHandler({DomainException.class})
    protected ResponseEntity<Object> handleConflict(
      RuntimeException ex, WebRequest request) {
       return handleExceptionInternal(ex, ex.getMessage(), 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
       
    }
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler({Throwable.class})
    protected ResponseEntity<Object> handleConflictThrow(
      RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Ha ocurrido un error....", 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
