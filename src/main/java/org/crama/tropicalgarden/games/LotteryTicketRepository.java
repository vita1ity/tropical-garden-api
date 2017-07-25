package org.crama.tropicalgarden.games;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryTicketRepository extends JpaRepository<LotteryTicket, Long>{

	List<LotteryTicket> findByLottery(Lottery lottery);
	
}
