package org.crama.tropicalgarden.users;

import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {
  
	private static final long serialVersionUID = -9110359854626735552L;
	
	private User user;
 
    public UserRegisteredEvent(User user) {
        super(user);
         
        this.user = user;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}