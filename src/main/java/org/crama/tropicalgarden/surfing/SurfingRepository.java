package org.crama.tropicalgarden.surfing;

import java.util.List;

import org.crama.tropicalgarden.users.User;
import org.crama.tropicalgarden.users.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurfingRepository extends JpaRepository<SurfingWebsite, Long> {

	List<SurfingWebsite> findByStatus(WebsiteStatus status);
	
	List<SurfingWebsite> findByUser(User user);
	
}
