package org.crama.tropicalgarden.users.passwordreset;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

import org.crama.tropicalgarden.mail.MailService;
import org.crama.tropicalgarden.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

	@Autowired
	private PasswordResetRepository passwordResetTokenRepository;
	
	@Autowired
	private MailService mailService;
	
	public void generateResetPasswordToken(User user) {
		
		String token = UUID.randomUUID().toString();
		
		LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
	    PasswordResetToken tokenObj = new PasswordResetToken(token, user, tomorrow);
	    //user.setToken(tokenObj);
	    passwordResetTokenRepository.save(tokenObj);
	    
        
        mailService.sendResetPasswordLink(user, token);
	
	}
	
	public String validateToken(long id, String token) {
		PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
	    if ((passToken == null) || (passToken.getUser().getId() != id)) {
	        return "invalidToken";
	    }
	 
	    
	    LocalDateTime expireDate = passToken.getExpiryDate();
	    if (expireDate.isBefore(LocalDateTime.now())) {
	    	return "tokenExpired";
	    }
	   
	    return null;
	}

	
	
}
