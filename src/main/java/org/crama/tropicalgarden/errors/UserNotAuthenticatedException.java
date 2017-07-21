package org.crama.tropicalgarden.errors;

public class UserNotAuthenticatedException extends MessageException {

	private static final long serialVersionUID = -872445069905281043L;

	public UserNotAuthenticatedException(String code, String message) {
		super(code, message);
		
	}
	
	
	
}
