package org.crama.tropicalgarden.statistics;

import java.util.Locale;

import org.crama.tropicalgarden.errors.ObjectNotFoundException;
import org.crama.tropicalgarden.errors.UserNotAuthenticatedException;
import org.crama.tropicalgarden.users.User;
import org.crama.tropicalgarden.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserStatisticsController {

	@Autowired
	private UserStatisticsService userStatisticsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value = "/api/user/stats", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public UserStatistics getUserStats(@RequestParam(required = false) Long id) 
			throws UserNotAuthenticatedException, ObjectNotFoundException, NoSuchMessageException {
		
		User user = userService.getPrincipal();
		
		if (id != null) {
		
			user = userService.getUserById(id);
			if (user == null) {
				throw new ObjectNotFoundException("message.userNotFound", messages.getMessage("message.userNotFound", null, Locale.ENGLISH));
			}
			 
		}
		
		UserStatistics userStats = userStatisticsService.getStatistics(user);
		return userStats;
		
	}
	
}
