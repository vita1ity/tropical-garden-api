package org.crama.tropicalgarden.users;

import org.crama.tropicalgarden.errors.ObjectNotFoundException;
import org.crama.tropicalgarden.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserRegisterValidator implements Validator {

	private UserService userService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {

        ValidationUtils.rejectIfEmpty(e, "username", "error.username.empty");
        ValidationUtils.rejectIfEmpty(e, "email", "error.email.empty");
        ValidationUtils.rejectIfEmpty(e, "password", "error.password.empty");
        User user = (User) target;

        int usernameLength = user.getUsername().length();
        if (usernameLength < 3 || usernameLength > 50) {
        
        	e.rejectValue("username", "error.username.invalidLength");
        }
        int emailLength = user.getEmail().length();
        if (emailLength < 5 || emailLength > 50) {
        	e.rejectValue("email", "error.email.invalidLength");
        }
        
        if (user.getPassword().length() < 6 || user.getPassword().length() > 50) {
        	e.rejectValue("password", "error.password.invalidLength");
        }
        
        EmailValidator emailValidator = new EmailValidator();
	    if (!emailValidator.validate(user.getEmail())) {
	    	e.rejectValue("email", "error.email.notValid");
	    }
	    
        User userFromDB;
		try {
			userFromDB = userService.getUserByEmail(user.getEmail());
			e.rejectValue("email", "error.email.alreadyExist");
		} catch (ObjectNotFoundException | NoSuchMessageException e1) {
			//user not found - do nothing
		}
		
        userFromDB = userService.getUserByUsername(user.getUsername());
        if (userFromDB != null) {
        	e.rejectValue("username", "error.username.alreadyExist");
        }
        
        
        logger.info("User password: " + user.getPassword());
        logger.info("User confirm password: " + user.getConfirmPassword());
        
        
        if (!user.getPassword().equals(user.getConfirmPassword())) {
        	e.rejectValue("confirmPassword" , "error.confirmPassword.notEqual");
        }
        
    }

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}	
    
    
	
}
