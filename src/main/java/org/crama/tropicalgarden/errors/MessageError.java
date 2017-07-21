package org.crama.tropicalgarden.errors;

import java.io.Serializable;

public class MessageError implements Serializable {

	private static final long serialVersionUID = 6467347372885426980L;

	private String errorCode;
	
	private String errorMessage;

	public MessageError(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	
	
}
