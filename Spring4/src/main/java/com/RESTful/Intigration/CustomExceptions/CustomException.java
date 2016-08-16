package com.RESTful.Intigration.CustomExceptions;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMsg;
	
	public CustomException(){
		super();
	}
	
	public CustomException(String errorMsg){
		super(errorMsg);
		this.errorMsg=errorMsg;
	}
	
	public String getErrorMessage(){
		return errorMsg;
	}
}
