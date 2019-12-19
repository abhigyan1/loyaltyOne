package com.loyaltyone.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SubmissionExceptionHandler {
	
	@ExceptionHandler(value = SubmissionsNotfoundException.class)
	public ResponseEntity<Object> exception(SubmissionsNotfoundException exception) {
	      return new ResponseEntity<>("Submissions not found: Possible reason- Name doesn not exist", HttpStatus.NOT_FOUND);
	   }
	@ExceptionHandler(value = TemperatureException.class)
	public ResponseEntity<Object> temperatureException(SubmissionsNotfoundException exception) {
	      return new ResponseEntity<>("malformed url", HttpStatus.BAD_REQUEST);
	   }
	
}
