package com.loyaltyone.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

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
	@ExceptionHandler(value = ResponseNotfoundException.class)
	public ResponseEntity<Object> responseException(SubmissionsNotfoundException exception) {
	      return new ResponseEntity<>("Response not found", HttpStatus.NOT_FOUND);
	   }

	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
	  ErrorMessage ErrorMessage = new ErrorMessage( ex.getMessage(), request.getDescription(false));
	  return new ResponseEntity<>(ErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	
}
