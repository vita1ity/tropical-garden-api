package org.crama.tropicalgarden.games;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Long>{

	List<Lottery> findByLotteryStatus(LotteryStatus lotteryStatus);

	

}


