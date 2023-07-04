package com.nithi.restapi.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nithi.restapi.users.UserNotFoundException;


@ControllerAdvice // it respond to all @Controller response if any exception happens
public class CustomizedResponeEntityErrorHandler extends ResponseEntityExceptionHandler {
	
	// 500 - INTERNAL_SERVER_ERROR
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) 
			throws Exception {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false)); // customized error object
		
		return new  ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	// 404 - // 500 - INTERNAL_SERVER_ERROR
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) 
			throws Exception {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false)); // customized error object
		
		return new  ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	// 400 - Bad request 
	// validation (fields)
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, 
			WebRequest request) {
		
		
		List<ErrorDetails> errorList = new ArrayList<>();

		for (int i = 0; i < ex.getErrorCount(); i++) {
			 ErrorDetails errorDetails = new ErrorDetails(
			            LocalDateTime.now(),
			            "Field: " + ex.getFieldErrors().get(i).getField() + 
			            ", Message: " + ex.getFieldErrors().get(i).getDefaultMessage(),
			            request.getDescription(false)
			    ); // customized error object
			 errorList.add(errorDetails);
		}
		
		// to return error  field one by one
		
//		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
//				ex.getFieldError().getDefaultMessage(),
//				request.getDescription(false)); // customized error object
//		return new  ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
		
		return new  ResponseEntity<Object>(errorList, HttpStatus.BAD_REQUEST);
	
	}
	
		

}
