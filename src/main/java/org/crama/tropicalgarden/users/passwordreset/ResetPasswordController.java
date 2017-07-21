package org.crama.tropicalgarden.users.passwordreset;

import java.util.Locale;

import org.crama.tropicalgarden.errors.MessageException;
import org.crama.tropicalgarden.errors.ObjectNotFoundException;
import org.crama.tropicalgarden.users.User;
import org.crama.tropicalgarden.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetPasswordController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordResetService passwordResetTokenService;
	
	@Autowired
    private MessageSource messages;
	
	@RequestMapping(value="/api/user/reset-password", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void getResetPasswordToken(@RequestParam String email) throws ObjectNotFoundException, NoSuchMessageException {
		
		User user = userService.getUserByEmail(email);
		
		passwordResetTokenService.generateResetPasswordToken(user);
		
	}
	
	@RequestMapping(value="/api/user/reset-password-link", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public boolean confirmResetPasswordToken(@RequestParam("id") long id, @RequestParam("token") String token) throws NoSuchMessageException, MessageException {
     
		String result = passwordResetTokenService.validateToken(id, token);
	    if (result != null) {
	    	
	    	throw new MessageException("auth.message." + result, messages.getMessage("auth.message." + result, null, Locale.ENGLISH));
	    	
	    }
	    
		return true;
	}
	
	@RequestMapping(value="/api/user/change-password", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean changePassword(@RequestBody ChangePasswordJSON changePasswordJSON) throws NoSuchMessageException, MessageException {
     
		String result = passwordResetTokenService.validateToken(changePasswordJSON.getId(), changePasswordJSON.getToken());
	    if (result != null) {
	    	
	    	throw new MessageException("auth.message." + result, messages.getMessage("auth.message." + result, null, Locale.ENGLISH));
	    	
	    }
	    
	    else {
	    	
	    	User user = userService.getUserById(changePasswordJSON.getId());
	    	
	    	userService.changePassword(user, changePasswordJSON.getPassword(), changePasswordJSON.getConfirmPassword());

			return true;
	    }
	    
	}
	
	@RequestMapping(value="/api/user/change-password-old", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean changePasswordOld(@RequestBody OldNewPasswordJSON oldNewPasswordJSON) throws NoSuchMessageException, MessageException {
     
		User user = userService.getPrincipal();
		
		userService.setNewPassword(oldNewPasswordJSON, user);
		
		return true;
	}
	
}
