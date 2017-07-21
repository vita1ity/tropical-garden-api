package org.crama.tropicalgarden.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	public void createVerificationToken(User user, String token) {
		
	    VerificationToken tokenObj = new VerificationToken(token, user);
	    user.setToken(tokenObj);
	    verificationTokenRepository.save(tokenObj);
	
	}
	
	public VerificationToken getVerificationToken(String token) {
		
		return verificationTokenRepository.findByToken(token);
	}

	
	
}
