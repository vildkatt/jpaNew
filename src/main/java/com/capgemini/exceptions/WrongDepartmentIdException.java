package com.capgemini.exceptions;

public class WrongDepartmentIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrongDepartmentIdException (String message) {
	    super(message);
	}
	
	
	public WrongDepartmentIdException () {
	    super("The Department Id cannot be null or empty");
	}
}
