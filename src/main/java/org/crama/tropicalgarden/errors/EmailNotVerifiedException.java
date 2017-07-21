package org.crama.tropicalgarden.errors;

public class EmailNotVerifiedException extends MessageException {

	private static final long serialVersionUID = -872445069905281043L;


	public EmailNotVerifiedException(String code, String message) {
		super(code, message);
		
	}


	
}
