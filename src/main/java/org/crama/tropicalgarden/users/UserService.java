package org.crama.tropicalgarden.users;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.crama.tropicalgarden.errors.MessageException;
import org.crama.tropicalgarden.errors.ObjectNotFoundException;
import org.crama.tropicalgarden.errors.UserNotAuthenticatedException;
import org.crama.tropicalgarden.mail.MailService;
import org.crama.tropicalgarden.users.passwordreset.OldNewPasswordJSON;
import org.crama.tropicalgarden.utils.Tokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private VerificationService verificationService;
	
	@Autowired
    private MessageSource messages;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public User saveUser(User user) {
		
		user.setRole(UserRole.ROLE_USER);
		user.setUserState(UserState.INACTIVE);
		user.setOnline(false);
		//user.setEmailVerificationLink(Tokener.randomString(16));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(user);
		
		String token = UUID.randomUUID().toString();
        verificationService.createVerificationToken(user, token);
        
        mailService.sendEmailVerificationLink(user);
		
		return savedUser;
	
	}
	
	public User getUserByEmail(String email) throws ObjectNotFoundException, NoSuchMessageException {
		
		User user = userRepository.findByEmail(email);
		
		if (user == null) {
			throw new ObjectNotFoundException("message.userNotFound", messages.getMessage("message.userNotFound", null, Locale.ENGLISH));
		}
		
		return user;
		
	}
	
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
		
	}
	
	public User getUserByUsernameOrEmail(String value) {
		User user = userRepository.findByUsername(value);
		return user == null? userRepository.findByEmail(value): user;
	}
	
	public User getPrincipal() throws UserNotAuthenticatedException {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		if ((authentication == null) || (!authentication.isAuthenticated())) {
		  
			throw new UserNotAuthenticatedException("auth.message.notAuthenticated", messages.getMessage("auth.message.notAuthenticated", null, Locale.ENGLISH));
		}
	  
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		User loginUser = getUserByUsernameOrEmail(userDetails.getUsername());
		return loginUser;
	  
	}
	
	


	public void setUserActive(User user) {
		
		user.setUserState(UserState.ACTIVE);
		userRepository.save(user);
		
	}

	public void changePassword(User user, String password, String confirmPassword) throws NoSuchMessageException, MessageException {
		
		if (password.length() < 6 || password.length() > 30) {
			
			throw new MessageException("error.password.invalidLength", messages.getMessage("error.password.invalidLength", null, Locale.ENGLISH));
		}
		
		if (!password.equals(confirmPassword)) {
		
			throw new MessageException("error.confirmPassword.notEqual", messages.getMessage("error.confirmPassword.notEqual", null, Locale.ENGLISH));
		
		}
		
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		
	}

	public User getUserById(long id) throws ObjectNotFoundException, NoSuchMessageException {
		try {
			User user = userRepository.getOne(id);
			logger.info("User: " + user);
			return user;
		}
		catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("message.userNotFound", messages.getMessage("message.userNotFound", null, Locale.ENGLISH));
		}
		
				
	
	}

	public void setNewPassword(OldNewPasswordJSON oldNewPasswordJSON, User user) throws NoSuchMessageException, MessageException {
		
		
		if (!passwordEncoder.matches(oldNewPasswordJSON.getOldPassword(), user.getPassword())) {
			
			throw new MessageException("error.password.invalidPassword", messages.getMessage("error.password.invalidPassword", null, Locale.ENGLISH));
			
		}
		
		String password = oldNewPasswordJSON.getPassword();
		String confirmPassword = oldNewPasswordJSON.getConfirmPassword();
		if (password.length() < 6 || password.length() > 30) {
			
			throw new MessageException("error.password.invalidLength", messages.getMessage("error.password.invalidLength", null, Locale.ENGLISH));
		}
		
		if (!password.equals(confirmPassword)) {
		
			throw new MessageException("error.confirmPassword.notEqual", messages.getMessage("error.confirmPassword.notEqual", null, Locale.ENGLISH));
		
		}
		
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		
	}

	public void changeGameplayMode(User user, GameplayMode gameplayMode) {
		
		user.setGameplayMode(gameplayMode);
		userRepository.save(user);
		
	}

	
	

}
