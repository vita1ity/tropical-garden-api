package org.crama.tropicalgarden.errors;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerValidationHandler {
	
	@Autowired
	private MessageSource msgSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<ValidationError> processValidationError(MethodArgumentNotValidException ex) {
		
		BindingResult result = ex.getBindingResult();
		List<FieldError> allErrors = result.getFieldErrors();
		
		Locale currentLocale = LocaleContextHolder.getLocale();
		
		List<ValidationError> errors = allErrors.stream()
                .map(e -> new ValidationError(e.getField(), e.getCode(), msgSource.getMessage(e.getCode(), null, currentLocale)))
                .collect(Collectors.toList());
		
		return errors;
  
	}
	
	@ExceptionHandler(MessageException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageError processErrorMessages(MessageException ex) {
		
		String code = ex.getCode();
		String message = ex.getMessage();
		
		MessageError error = new MessageError(code, message);
		
		return error;
  
	}
	
}
