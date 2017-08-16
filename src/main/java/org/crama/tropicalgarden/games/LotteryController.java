package org.crama.tropicalgarden.games;

import java.util.List;

import org.crama.tropicalgarden.errors.UserNotAuthenticatedException;
import org.crama.tropicalgarden.users.User;
import org.crama.tropicalgarden.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotteryController {

	@Autowired
	private LotteryService lotteryService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/api/games/lottery/buy", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
    public boolean buyLotteryTicket() throws UserNotAuthenticatedException {
		
		Lottery lottery = lotteryService.getOpenLottery();
		
		if (lottery == null) {
			lottery = lotteryService.createLottery();
		}
		
		List<LotteryTicket> lotteryTickets = lotteryService.getLotteryTickets(lottery);
		
		User user = userService.getPrincipal();
		
		LotteryTicket newTicket = lotteryService.createLotteryTicket(user, lotteryTickets.size() + 1, lottery);
		lotteryTickets.add(newTicket);
		
		if (lotteryTickets.size() == 10) {
			
			lotteryService.playLottery(lottery, lotteryTickets);
			
		}
		
		return true;
        
    }
	
	@RequestMapping(value="/api/games/lottery/get-open-tickets", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<LotteryTicket> getOpenLotteryTickets() throws UserNotAuthenticatedException {
		
		Lottery lottery = lotteryService.getOpenLottery();
		List<LotteryTicket> lotteryTickets = lotteryService.getLotteryTickets(lottery);
		
		return lotteryTickets;
		 
	}
	
	@RequestMapping(value="//api/games/lottery/get-played", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Lottery> getPlayedLottery() throws UserNotAuthenticatedException {
		
		Lottery playedLottery = lotteryService.getPlayedLottery();
		List<Lottery> playedLotteries = lotteryService.getPlayedLotteries(playedLottery);
		
		return playedLotteries;
		
	}
	
	
	
}
