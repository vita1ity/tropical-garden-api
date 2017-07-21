package org.crama.tropicalgarden.statistics;

import java.time.LocalDate;

import org.crama.tropicalgarden.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyStatsService {

	@Autowired
	private DailyStatsRepository dailyStatsRepository;
	
	public UserDailyStats getDailyStats(User user) {
		
		UserDailyStats dailyStats = dailyStatsRepository.findByUser(user);
		
		return dailyStats;
	}

	public void createDailyStats(User user) {
		
		UserDailyStats dailyStats = new UserDailyStats(user, LocalDate.now()); 
		dailyStatsRepository.save(dailyStats);
		
	}

}
