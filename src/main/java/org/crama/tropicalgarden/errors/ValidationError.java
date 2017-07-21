package org.crama.tropicalgarden.errors;

import java.io.Serializable;

public class ValidationError implements Serializable {
	
	private static final long serialVersionUID = -4165803156925537585L;

	private String fields;
	
	private String errorCode;
	
	private String errorMessage;
	
	
	public ValidationError(String fields, String errorCode, String errorMessage) {
		super();
		this.fields = fields;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
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
		return "ValidationError [fields=" + fields + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ "]";
	}

	
}
