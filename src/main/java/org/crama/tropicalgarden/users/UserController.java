package org.crama.tropicalgarden.users;

import java.util.Locale;

import javax.validation.Valid;

import org.crama.tropicalgarden.errors.ObjectNotFoundException;
import org.crama.tropicalgarden.errors.RegistrationException;
import org.crama.tropicalgarden.errors.UserNotAuthenticatedException;
import org.crama.tropicalgarden.referrals.PartnerService;
import org.crama.tropicalgarden.statistics.DailyStatsService;
import org.crama.tropicalgarden.statistics.UserStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VerificationService verificationService;
	
	@Autowired
    private MessageSource messages;
	
	@Autowired
	private UserStatisticsService userStatisticsService;
	
	@Autowired
	private DailyStatsService dailyStatsService;
	
	@Autowired
	private PartnerService partnerService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    UserRegisterValidator validator = new UserRegisterValidator();
	    validator.setUserService(userService);
		binder.setValidator(validator);
		
	}
	
	@RequestMapping(value="/api/user/register", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
    public boolean register(@RequestParam(required = false) Long ref, @Valid @RequestBody User user, WebRequest request) {
		
		User registered = userService.saveUser(user);
		
		userStatisticsService.createUserStats(user);
		dailyStatsService.createDailyStats(user);
		
		//add partner
		if (ref != null) {
			try {
				
				User referral = userService.getUserById(ref);
				partnerService.savePartner(referral, user);
				
			} catch (ObjectNotFoundException e) {
				
				logger.error("Can't find referral with id: " + ref + ". Partner is not created");
			}
				
			
		}
		
		return registered != null;
        
    }
	
	@RequestMapping(value="/api/user/activate", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public boolean confirmRegistration(@RequestParam String token) throws NoSuchMessageException, RegistrationException {
     
	    VerificationToken verificationToken = verificationService.getVerificationToken(token);
	    if (verificationToken == null) {
	    	
	    	throw new RegistrationException("auth.message.invalidToken", messages.getMessage("auth.message.invalidToken", null, Locale.ENGLISH));
	    	
	    }
	     
	    User user = verificationToken.getUser();
	   
	    userService.setUserActive(user);
	    
	    return true; 
		
	}
	
	
	
	@RequestMapping(value="/api/user", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public User getUser(@RequestParam(required = false) Long id) throws UserNotAuthenticatedException, ObjectNotFoundException, NoSuchMessageException {
		
		User user = userService.getPrincipal();
		
		if (id == null) {
			return user;
		}
		else {
			user = userService.getUserById(id);
			if (user == null) {
				throw new ObjectNotFoundException("message.userNotFound", messages.getMessage("message.userNotFound", null, Locale.ENGLISH));
			}
			return user; 
		}
        
    }
	
	@RequestMapping(value = "/api/user/gameplay-mode", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void changeGameplayMode(@RequestParam GameplayMode mode) throws UserNotAuthenticatedException {
		
		User user = userService.getPrincipal();
		userService.changeGameplayMode(user, mode);
		
		
	}
	
	
	
}
