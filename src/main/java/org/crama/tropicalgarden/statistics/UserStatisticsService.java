package org.crama.tropicalgarden.statistics;

import java.time.LocalDateTime;

import org.crama.tropicalgarden.config.GameplayProperties;
import org.crama.tropicalgarden.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStatisticsService {

	@Autowired
	private UserStatisticsRepository userStatisticsRepository;
	
	@Autowired
	private GameplayProperties gameplayProperties;
	
	public UserStatistics getStatistics(User user) {
		
		UserStatistics userStats = userStatisticsRepository.findByUser(user);
		
		return userStats;
	}

	public void createUserStats(User user) {
		
		UserStatistics userStats = new UserStatistics(user, LocalDateTime.now(), gameplayProperties.getBalanceForBuying());
		userStatisticsRepository.save(userStats);
		
	}
	
	public void saveStats(UserStatistics stats) {
		userStatisticsRepository.save(stats);
	}

}
