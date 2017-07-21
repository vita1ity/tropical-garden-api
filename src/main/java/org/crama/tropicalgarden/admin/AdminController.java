package org.crama.tropicalgarden.admin;

import java.util.List;

import javax.validation.Valid;

import org.crama.tropicalgarden.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@RequestMapping(value="/api/admin/all-users", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
    public List<User> getAllUsers() {
		
		
		//TODO complete
		return null;
        
    }
	
}
