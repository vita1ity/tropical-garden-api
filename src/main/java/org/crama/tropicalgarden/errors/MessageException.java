package org.crama.tropicalgarden.errors;

public class MessageException extends Exception {
	
	private static final long serialVersionUID = -5442617751982926732L;
	
	private String code;
	
	private String message;

	public MessageException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
