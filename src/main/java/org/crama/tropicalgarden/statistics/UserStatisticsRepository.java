package org.crama.tropicalgarden.statistics;

import org.crama.tropicalgarden.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Long>{

	public UserStatistics findByUser(User user);

	
}
