package org.crama.tropicalgarden.errors;

import java.io.Serializable;

public class ValidationError implements Serializable {
	
	private static final long serialVersionUID = -4165803156925537585L;

	private String field;
	
	private String errorCode;
	
	private String errorMessage;
	
	
	public ValidationError(String field, String errorCode, String errorMessage) {
		super();
		this.field = field;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getfield() {
		return field;
	}

	public void setfield(String field) {
		this.field = field;
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

	@Override
	public String toString() {
		return "ValidationError [field=" + field + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ "]";
	}

	
}
