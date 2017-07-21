package org.crama.tropicalgarden.errors;

public class ObjectNotFoundException extends MessageException {

	private static final long serialVersionUID = -1395609507982917309L;

	public ObjectNotFoundException(String code, String message) {
		super(code, message);
	}
	
}
