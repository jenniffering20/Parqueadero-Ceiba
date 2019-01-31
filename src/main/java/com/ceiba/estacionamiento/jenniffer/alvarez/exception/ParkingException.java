package com.ceiba.estacionamiento.jenniffer.alvarez.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@ControllerAdvice
public class ParkingException extends ResponseEntityExceptionHandler {
	
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(Exception.class)
	 public void defaultExceptionHandler() {
	        // Nothing to do
	    }
	 @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
	 protected ResponseEntity<Object> handleMethodArgumentsNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		 
		 ApiErrorResponse response  = new ApiErrorResponse.ApiErrorResponseBuilder()
				 .withStatus(status)
				 .withError_code(status.BAD_REQUEST.name())
				 .withMessage(ex.getLocalizedMessage()).build();
		  			
		 		return new ResponseEntity<>(response, response.getStatus());
		 
	 }
	 

}
