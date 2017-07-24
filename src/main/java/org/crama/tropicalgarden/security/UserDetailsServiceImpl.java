package org.crama.tropicalgarden.security;

import java.util.ArrayList;
import java.util.Collection;

import org.crama.tropicalgarden.users.User;
import org.crama.tropicalgarden.users.UserService;
import org.crama.tropicalgarden.users.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 
		User user = userService.getUserByUsernameOrEmail(username);

		if (user == null) {
			
			throw new UsernameNotFoundException("Could not find the user '"
	                  + username + "'");
			
		}
		
		
		boolean isActive = false;
		if (user.getUserState().equals(UserState.ACTIVE)) {
			isActive = true; 
			//throw new DisabledException("Email address is not verified");
		
		}

	    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    
	    
	    logger.info("User role (loadUserByUsername): " + user.getRole().toString());
	    
	    authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
	     
	     
	    org.springframework.security.core.userdetails.User securityUser = 
	    		new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), isActive, true, true, true, authorities);
	    return securityUser;
        
	}
	 
	
}
