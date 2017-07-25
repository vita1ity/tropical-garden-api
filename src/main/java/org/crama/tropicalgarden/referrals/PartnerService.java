package org.crama.tropicalgarden.referrals;

import org.crama.tropicalgarden.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {

	@Autowired
	private PartnerRepository partnerRepository;
	
	public Partner savePartner(User user, User partner) {
		
		Partner partnerObj = new Partner(user, partner);
		Partner partnerSaved = partnerRepository.save(partnerObj);
	
		return partnerSaved;
	}

	
	
}
