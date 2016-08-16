package com.RESTful.Intigration.CustomExceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ErrorResponse error;
	
	@ExceptionHandler(Exception.class)
	public ErrorResponse exceptionHandler(Exception ex){
		error.setErrorCode(HttpStatus.EXPECTATION_FAILED.value());
		error.setErrorMsg("Runtime or Server error");
		return error;
		
	}
}
