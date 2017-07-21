package org.crama.tropicalgarden.surfing;

import java.util.List;
import java.util.Locale;

import javax.persistence.EntityNotFoundException;

import org.crama.tropicalgarden.errors.ObjectNotFoundException;
import org.crama.tropicalgarden.errors.SurfingException;
import org.crama.tropicalgarden.mail.MailService;
import org.crama.tropicalgarden.statistics.UserStatistics;
import org.crama.tropicalgarden.statistics.UserStatisticsService;
import org.crama.tropicalgarden.users.User;
import org.crama.tropicalgarden.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;


@Service
public class SurfingService {

	@Autowired
	private SurfingRepository surfingRepository;
	
	@Autowired
    private MessageSource messages;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserStatisticsService userStatsService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public SurfingWebsite saveWebsite(SurfingWebsite website) {
		
		website.setStatus(WebsiteStatus.CREATED);
		SurfingWebsite savedWebsite = surfingRepository.save(website);
		return savedWebsite;
	
	}

	public SurfingWebsite getWebsiteById(long id) throws ObjectNotFoundException, NoSuchMessageException {
		
		
		SurfingWebsite website;
		try {
			website = surfingRepository.getOne(id);
			logger.info("Website: " + website);
		}
		
		catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("ex.surfing.notFound", messages.getMessage("ex.surfing.notFound",  null, Locale.ENGLISH));
		}
		
		return website;
	}

	public void editWebsite(SurfingWebsite oldWebsite, SurfingWebsite website) {
		
		oldWebsite.setActuallyRedirect(website.getActuallyRedirect());
		oldWebsite.setIsHighlighted(website.getIsHighlighted());
		oldWebsite.setPointsForView(website.getPointsForView());
		oldWebsite.setSiteDescription(website.getSiteDescription());
		oldWebsite.setSiteName(website.getSiteName());
		oldWebsite.setSiteUrl(website.getSiteUrl());
		oldWebsite.setViewTime(website.getViewTime());
		
		surfingRepository.save(oldWebsite);
		
	}

	public void deleteWebsite(long id) {
		
		surfingRepository.delete(id);
		
	}

	public void setOnReview(User user, SurfingWebsite website) throws SurfingException, NoSuchMessageException {
		
		if (!user.equals(website.getUser())) {
			throw new SurfingException("ex.surfing.notOwner", messages.getMessage("ex.surfing.notOwner", 
					null, Locale.ENGLISH));
		}
		
		if (!website.getStatus().equals(WebsiteStatus.CREATED)) {
			throw new SurfingException("ex.surfing.wrongStatus", messages.getMessage("ex.surfing.wrongStatus", 
					new Object[] {website.getStatus(), WebsiteStatus.CREATED}, Locale.ENGLISH));
		}
		website.setStatus(WebsiteStatus.ON_REVIEW);
		surfingRepository.save(website);
		
		mailService.sendWebsiteApproval(website);
		
		
	}

	public void approve(SurfingWebsite website) throws SurfingException, NoSuchMessageException {
		
		if (!website.getStatus().equals(WebsiteStatus.ON_REVIEW)) {
			throw new SurfingException("ex.surfing.wrongStatus", messages.getMessage("ex.surfing.wrongStatus", 
					new Object[] {website.getStatus(), WebsiteStatus.ON_REVIEW}, Locale.ENGLISH));
		}
		
		website.setStatus(WebsiteStatus.APPROVED);
		surfingRepository.save(website);
		
		//TODO send user notification about approval
		
	}

	public void depositFunds(User user, SurfingWebsite website, long amount) throws SurfingException, NoSuchMessageException {
		
		if (!user.equals(website.getUser())) {
			throw new SurfingException("ex.surfing.notOwner", messages.getMessage("ex.surfing.notOwner", 
					null, Locale.ENGLISH));
		}
		
		if (website.getPointsForView() > amount) {
			throw new SurfingException("ex.surfing.funds.insufficient", messages.getMessage("ex.surfing.funds.insufficient", 
					new Object[] {website.getPointsForView()}, Locale.ENGLISH));
		}
		
		//TODO make different payment options available
		UserStatistics userStats = userStatsService.getStatistics(user);
		if (userStats.getBalanceForWithdrawal() < amount) {
			throw new SurfingException("ex.surfing.user.insufficient", messages.getMessage("ex.surfing.user.insufficient", 
					null, Locale.ENGLISH));
		}
		userStats.setBalanceForWithdrawal(userStats.getBalanceForWithdrawal() - amount);
		userStatsService.saveStats(userStats);		
		
		website.setSurfingBudget(website.getSurfingBudget() + amount);
		
		long numberOfViews = website.getSurfingBudget() / website.getPointsForView();
		website.setViewsAvailable((int) numberOfViews);
		surfingRepository.save(website);
		
		
	}

	public void run(User user, SurfingWebsite website) throws SurfingException, NoSuchMessageException {
		
		if (!user.equals(website.getUser())) {
			throw new SurfingException("ex.surfing.notOwner", messages.getMessage("ex.surfing.notOwner", 
					null, Locale.ENGLISH));
		}
		
		if (!(website.getStatus().equals(WebsiteStatus.APPROVED) || website.getStatus().equals(WebsiteStatus.PAUSED))) {
			throw new SurfingException("ex.surfing.wrongStatus", messages.getMessage("ex.surfing.wrongStatus", 
					new Object[] {website.getStatus(), WebsiteStatus.APPROVED}, Locale.ENGLISH));
		}
		if (website.getViewsAvailable() <= 0) {
			throw new SurfingException("ex.surfing.noViewsAvailable", messages.getMessage("ex.surfing.noViewsAvailable", 
					null, Locale.ENGLISH));
		}
		
		
		website.setStatus(WebsiteStatus.RUNNING);
		surfingRepository.save(website);
		
	}

	public void pause(SurfingWebsite website) throws SurfingException, NoSuchMessageException {
		
		if (!website.getStatus().equals(WebsiteStatus.RUNNING)) {
			throw new SurfingException("ex.surfing.wrongStatus", messages.getMessage("ex.surfing.wrongStatus", 
					new Object[] {website.getStatus(), WebsiteStatus.RUNNING}, Locale.ENGLISH));
		}
		
		website.setStatus(WebsiteStatus.PAUSED);
		surfingRepository.save(website);
		
	}

	public List<SurfingWebsite> getByStatus(WebsiteStatus status) {
		
		return surfingRepository.findByStatus(status);
		
	}

	public List<SurfingWebsite> getAll() {
		return surfingRepository.findAll();
	}

	public List<SurfingWebsite> getByUser(User user) {
		return surfingRepository.findByUser(user);
	}

	public void view(User user, SurfingWebsite website) throws SurfingException, NoSuchMessageException {
		
		if (!website.getStatus().equals(WebsiteStatus.RUNNING)) {
			throw new SurfingException("ex.surfing.wrongStatus", messages.getMessage("ex.surfing.wrongStatus", 
					new Object[] {website.getStatus(), WebsiteStatus.RUNNING}, Locale.ENGLISH));
		}
		
		if (website.getViewsAvailable() == 0) {
			throw new SurfingException("ex.surfing.noViewsAvailable", messages.getMessage("ex.surfing.noViewsAvailable", 
					null, Locale.ENGLISH));
		}
		
		website.setNumOfViews(website.getNumOfViews() + 1);
		website.setSurfingBudget(website.getSurfingBudget() - website.getPointsForView());
		website.setViewsAvailable(website.getViewsAvailable() - 1);
		
		if (website.getViewsAvailable() == 0) {
			pause(website);
			
			//TODO send web site owner notification (not user)
			mailService.sendWebsitePausedNotification(website);
		}
		
		surfingRepository.save(website);
		
		UserStatistics userStats = userStatsService.getStatistics(user);
		userStats.setBalanceForWithdrawal(userStats.getBalanceForWithdrawal() + website.getPointsForView());
		userStats.setCoinsEarnedBySurfing(userStats.getCoinsEarnedBySurfing() + website.getPointsForView());
		
		userStatsService.saveStats(userStats);
		
	}
	
	
}
