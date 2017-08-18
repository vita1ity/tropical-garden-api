package org.crama.tropicalgarden.games;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.crama.tropicalgarden.config.GameplayProperties;
import org.crama.tropicalgarden.statistics.UserStatistics;
import org.crama.tropicalgarden.statistics.UserStatisticsService;
import org.crama.tropicalgarden.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotteryService {

	@Autowired
	private LotteryRepository lotteryRepository;
	
	@Autowired
	private LotteryTicketRepository lotteryTicketRepository;
	
	@Autowired
	private GameplayProperties gameplayProperties;
	
	@Autowired
	private UserStatisticsService userStatisticsService;
	
	Random rand = new Random();
	
	public Lottery getOpenLottery() {
		
		List<Lottery> lottery = lotteryRepository.findByLotteryStatus(LotteryStatus.OPEN);
		
		return lottery.get(0);
	}

	public List<LotteryTicket> getLotteryTickets(Lottery lottery) {
		
		List<LotteryTicket> tickets = lotteryTicketRepository.findByLottery(lottery);
		
		return tickets;
	}

	public Lottery createLottery() {
		
		Lottery lottery = new Lottery(LocalDate.now(), gameplayProperties.getBank());
		Lottery lotterySaved = lotteryRepository.save(lottery);
		
		return lotterySaved;
	}

	public LotteryTicket createLotteryTicket(User user, long ticketNum, Lottery lottery) {
		
		LotteryTicket lotteryTicket = new LotteryTicket(user, ticketNum, LocalDateTime.now(), lottery);
		
		UserStatistics userStatistics = userStatisticsService.getStatistics(user);
		userStatistics.setBalanceForBuyings(userStatistics.getBalanceForBuyings() - (long)lottery.getBank() / 10);
		
		userStatisticsService.saveStats(userStatistics);
		
		lotteryTicketRepository.save(lotteryTicket);
		
		return lotteryTicket;
		
	}

	public void playLottery(Lottery lottery, List<LotteryTicket> lotteryTickets) {
		
		int firstWinnerNumber = rand.nextInt(10);
		LotteryTicket firstWinner = lotteryTickets.remove(firstWinnerNumber);
		firstWinner.setWonAmount((long)(lottery.getBank() * 0.5));
		lottery.setFirstPlace(firstWinner);
		UserStatistics firstWinnerUser = userStatisticsService.getStatistics(firstWinner.getUser());
		firstWinnerUser.setBalanceForWithdrawal(firstWinnerUser.getBalanceForWithdrawal() + (long)(lottery.getBank() * 0.5));
		userStatisticsService.saveStats(firstWinnerUser);
		
		int secondWinnerNumber = rand.nextInt(9);
		LotteryTicket secondWinner = lotteryTickets.remove(secondWinnerNumber);
		secondWinner.setWonAmount((long)(lottery.getBank() * 0.25));
		lottery.setSecondPlace(secondWinner);
		UserStatistics secondWinnerUser = userStatisticsService.getStatistics(secondWinner.getUser());
		secondWinnerUser.setBalanceForWithdrawal(secondWinnerUser.getBalanceForWithdrawal() + (long)(lottery.getBank() * 0.25));
		userStatisticsService.saveStats(secondWinnerUser);
		
		int thirdWinnerNumber = rand.nextInt(8);
		LotteryTicket thirdWinner = lotteryTickets.remove(thirdWinnerNumber);
		thirdWinner.setWonAmount((long)(lottery.getBank() * 0.2));
		lottery.setThirdPlace(thirdWinner);
		UserStatistics thirdWinnerUser = userStatisticsService.getStatistics(thirdWinner.getUser());
		thirdWinnerUser.setBalanceForWithdrawal(thirdWinnerUser.getBalanceForWithdrawal() + (long)(lottery.getBank() * 0.2));
		userStatisticsService.saveStats(thirdWinnerUser);
		
		//TODO notify winners
		
		lottery.setLotteryStatus(LotteryStatus.PLAYED);
		
		lotteryTicketRepository.save(firstWinner);
		lotteryTicketRepository.save(secondWinner);
		lotteryTicketRepository.save(thirdWinner);
		
		lotteryRepository.save(lottery);
		
		createLottery();
	}
	
	public List<Lottery> getPlayedLotteries() {
		
		List<Lottery> playedLottery = lotteryRepository.findByLotteryStatus(LotteryStatus.PLAYED);
		
		return playedLottery;
	}

	
	
}
