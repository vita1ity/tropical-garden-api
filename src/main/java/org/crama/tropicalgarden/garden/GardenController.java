package org.crama.tropicalgarden.garden;

import java.util.List;

import org.crama.tropicalgarden.errors.GardenException;
import org.crama.tropicalgarden.errors.ObjectNotFoundException;
import org.crama.tropicalgarden.errors.UserNotAuthenticatedException;
import org.crama.tropicalgarden.users.User;
import org.crama.tropicalgarden.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GardenController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GardenService gardenService;
	
	@RequestMapping(value = "api/garden", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<UserTree> getUserGarden() throws UserNotAuthenticatedException {
		
		User user = userService.getPrincipal();
		
		List<UserTree> userGarden = gardenService.getUserGarden(user);
		
		return userGarden;
		
	}
	
	@RequestMapping(value = "api/garden/buy", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean buyTree(@RequestParam long treeId, @RequestParam int quantity) 
			throws UserNotAuthenticatedException, ObjectNotFoundException, NoSuchMessageException, GardenException {
		
		User user = userService.getPrincipal();
		
		TreeType tree = gardenService.getTreeType(treeId);
		
		gardenService.buyTree(user, tree, quantity);
		
		return true;
		
	}
	
	//user can collect fruits once in 10 minutes. The time can be adjusted in the property file
	@RequestMapping(value = "api/garden/collect", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean collectFruits() 
			throws UserNotAuthenticatedException, GardenException, NoSuchMessageException {
		
		User user = userService.getPrincipal();
		
		List<UserTree> userTreeList = gardenService.getUserGarden(user);
		
		gardenService.collectFruits(user, userTreeList);
		
		return true;
		
	}
	
	@RequestMapping(value = "api/garden/sell", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean sellFruits() 
			throws UserNotAuthenticatedException, GardenException, NoSuchMessageException {
		
		User user = userService.getPrincipal();
		
		List<UserTree> userTreeList = gardenService.getUserGarden(user);
		
		gardenService.sellFruits(user, userTreeList);
		
		return true;
		
	}
	
	@RequestMapping(value = "api/garden/all", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<TreeType> getAllTrees() throws UserNotAuthenticatedException {
		
		User user = userService.getPrincipal();
		
		List<TreeType> trees = gardenService.getAllTrees();
		
		return trees;
		
	}
	
	
	
}
