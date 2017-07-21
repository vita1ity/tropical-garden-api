package org.crama.tropicalgarden.users;

import java.util.UUID;

import org.crama.tropicalgarden.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<UserRegisteredEvent> {
  
    @Autowired
    private VerificationService verificationService;
  
    @Autowired
    private MailService mailService;
 
    @Override
    public void onApplicationEvent(UserRegisteredEvent event) {
        this.confirmRegistration(event);
    }
 
    private void confirmRegistration(UserRegisteredEvent event) {
    	
    	User user = event.getUser();
    	
        String token = UUID.randomUUID().toString();
        verificationService.createVerificationToken(user, token);
        
        mailService.sendEmailVerificationLink(user);
        
    }
}
