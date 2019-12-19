package com.loyaltyone.ExceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class DefaultExceptionHandler extends ResponseEntityExceptionHandler{
@ExceptionHandler(Exception.class)
public final ResponseEntity<ErrorMessage> exceptionHappened(Exception ex)
{
	ErrorMessage exceptionResponse =  new ErrorMessage(ex.getMessage(),"Something went wrong?");
	
	return new ResponseEntity<ErrorMessage> (exceptionResponse, 
			new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
}
}
