package org.crama.tropicalgarden.games;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Long>{

	Lottery findByLotteryStatus(LotteryStatus lotteryStatus);
	
}
