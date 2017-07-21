package org.crama.tropicalgarden.surfing;

import java.util.List;

import javax.validation.Valid;

import org.crama.tropicalgarden.errors.ObjectNotFoundException;
import org.crama.tropicalgarden.errors.SurfingException;
import org.crama.tropicalgarden.errors.UserNotAuthenticatedException;
import org.crama.tropicalgarden.users.User;
import org.crama.tropicalgarden.users.UserRegisterValidator;
import org.crama.tropicalgarden.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurfingController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SurfingWebsiteValidator validator = new SurfingWebsiteValidator();
		binder.setValidator(validator);
	}
	
	@Autowired
	private SurfingService surfingService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/api/surfing/add", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
    public boolean addWebsite(@Valid @RequestBody SurfingWebsite website) throws UserNotAuthenticatedException {
		
		User user = userService.getPrincipal();
		website.setUser(user);
		SurfingWebsite savedWebsite = surfingService.saveWebsite(website);
		
		return savedWebsite != null;
        
    }
	
	
	
	@RequestMapping(value="/api/surfing/edit/{id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void editWebsite(@PathVariable long id, @Valid @RequestBody SurfingWebsite website) 
    		throws UserNotAuthenticatedException, ObjectNotFoundException, NoSuchMessageException {
		
		SurfingWebsite oldWebsite = surfingService.getWebsiteById(id);
		
		surfingService.editWebsite(oldWebsite, website);
		
        
    }
	
	@RequestMapping(value="/api/surfing/delete/{id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void editWebsite(@PathVariable long id) 
    		throws UserNotAuthenticatedException, ObjectNotFoundException, NoSuchMessageException {
		
		surfingService.getWebsiteById(id);
		
		surfingService.deleteWebsite(id);
		
        
    }
	
	@RequestMapping(value="/api/surfing/review/{id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void sendWebsiteToReview(@PathVariable long id) 
    		throws UserNotAuthenticatedException, NoSuchMessageException, SurfingException, ObjectNotFoundException {
		
		User user = userService.getPrincipal();
		
		SurfingWebsite website = surfingService.getWebsiteById(id);
		
		surfingService.setOnReview(user, website);
		
        
    }
	
	@RequestMapping(value="/api/surfing/approve/{id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void approveWebsite(@PathVariable long id) 
    		throws UserNotAuthenticatedException, NoSuchMessageException, SurfingException, ObjectNotFoundException {
		
		SurfingWebsite website = surfingService.getWebsiteById(id);
		
		surfingService.approve(website);
		
        
    }
	
	@RequestMapping(value="/api/surfing/deposit/{id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void approveWebsite(@PathVariable long id, @RequestParam long amount) 
    		throws UserNotAuthenticatedException, NoSuchMessageException, SurfingException, ObjectNotFoundException {
		
		User user = userService.getPrincipal();
		
		SurfingWebsite website = surfingService.getWebsiteById(id);
		
		surfingService.depositFunds(user, website, amount);
		
        
    }
	
	@RequestMapping(value="/api/surfing/run/{id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void runWebsite(@PathVariable long id) 
    		throws UserNotAuthenticatedException, NoSuchMessageException, SurfingException, ObjectNotFoundException {
		User user = userService.getPrincipal();
		SurfingWebsite website = surfingService.getWebsiteById(id);
		
		surfingService.run(user, website);
		
        
    }
	
	@RequestMapping(value="/api/surfing/pause/{id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void pauseWebsite(@PathVariable long id) 
    		throws UserNotAuthenticatedException, NoSuchMessageException, SurfingException, ObjectNotFoundException {
		
		SurfingWebsite website = surfingService.getWebsiteById(id);
		
		surfingService.pause(website);
		
    }
	
	@RequestMapping(value="/api/surfing/all", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<SurfingWebsite> getByStatus(@RequestParam(required = false) WebsiteStatus status) 
    		throws UserNotAuthenticatedException{
		
		if (status != null) {
			return surfingService.getByStatus(status);
		} else {
			return surfingService.getAll();
		}
        
    }
	
	@RequestMapping(value="/api/surfing/users", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<SurfingWebsite> getByUser(@RequestParam(required = false) Long userId) 
    		throws UserNotAuthenticatedException, ObjectNotFoundException, NoSuchMessageException{
		
		User user;
		if (userId == null) {
			user = userService.getPrincipal();
		}
		else {
			user = userService.getUserById(userId);
		}
		
		return surfingService.getByUser(user);
	   
    }
	
	@RequestMapping(value="/api/surfing/view/{id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void viewWebsite(@PathVariable long id) 
    		throws UserNotAuthenticatedException, NoSuchMessageException, SurfingException, ObjectNotFoundException {
		
		User user = userService.getPrincipal();
		SurfingWebsite website = surfingService.getWebsiteById(id);
		
		surfingService.view(user, website);
		
    }
	
}
