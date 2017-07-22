package org.crama.tropicalgarden.garden;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.crama.tropicalgarden.config.GameplayProperties;
import org.crama.tropicalgarden.errors.GardenException;
import org.crama.tropicalgarden.errors.ObjectNotFoundException;
import org.crama.tropicalgarden.statistics.UserStatistics;
import org.crama.tropicalgarden.statistics.UserStatisticsService;
import org.crama.tropicalgarden.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class GardenService {

	@Autowired
	private UserTreeRepository userTreeRepository;
	
	@Autowired
	private TreeTypeRepository treeTypeRepository;
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	private UserStatisticsService userStatisticsService;
	
	@Autowired
	private GameplayProperties gameplayProperties;
	
	private static final Logger logger = LoggerFactory.getLogger(GardenService.class);

	public List<UserTree> getUserGarden(User user) {
		
		List<UserTree> userGarden = userTreeRepository.findByUser(user);
		return userGarden;
	}

	public TreeType getTreeType(long treeId) throws ObjectNotFoundException, NoSuchMessageException {
		treeTypeRepository.getOne(treeId);
		try {
			TreeType treeType = treeTypeRepository.getOne(treeId);
			logger.info("TreeType: " + treeType);
			return treeType;
		}
		catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("garden.treeNotFound", messages.getMessage("garden.treeNotFound", null, Locale.ENGLISH));
		}
	}

	public void buyTree(User user, TreeType tree, int quantity) throws GardenException, NoSuchMessageException {
		
		long price = tree.getPrice();
		
		UserStatistics userStats = userStatisticsService.getStatistics(user);
		
		long cost = price * quantity;
		
		logger.info("User money: " + userStats.getBalanceForBuyings() + ", trees price: " + cost);
		
		if (userStats.getBalanceForBuyings() < cost) {
			throw new GardenException("garden.notEnoughMoney", messages.getMessage("garden.notEnoughMoney", null, Locale.ENGLISH));
		}
		
		UserTree userTree = userTreeRepository.findByUserAndTreeType(user, tree);
		
		if (userTree == null) {
			userTree = new UserTree(user, tree, quantity);
		}
		else {
			userTree.setQuantity(userTree.getQuantity() + quantity);
		}
		
		userStats.setBalanceForBuyings(userStats.getBalanceForBuyings() - cost);
		userStatisticsService.saveStats(userStats);

		userTreeRepository.save(userTree);
		
	}

	public void collectFruits(User user, List<UserTree> userTreeList) throws GardenException, NoSuchMessageException {
		
		UserStatistics userStats = userStatisticsService.getStatistics(user);
		
		LocalDateTime time = LocalDateTime.now().minusMinutes(gameplayProperties.getUpdateTimeMin());
		LocalDateTime lastCollect = userStats.getLastFruitCollection();
		if (lastCollect != null && lastCollect.isAfter(time)) {
			
			throw new GardenException("garden.earlyUpdate", messages.getMessage("garden.earlyUpdate", null, Locale.ENGLISH));
			
		}
		
		//collect all fruits available
		for (UserTree tree: userTreeList) {
			tree.setNumOfStorageFruits(tree.getNumOfStorageFruits() + tree.getNumOfTreeFruits());
			tree.setNumOfTreeFruits(0);
			userTreeRepository.save(tree);
		}
		
		userStats.setLastFruitCollection(LocalDateTime.now());
		userStatisticsService.saveStats(userStats);
		
	}

	public void sellFruits(User user, List<UserTree> userTreeList) {
		
		UserStatistics userStats = userStatisticsService.getStatistics(user);
		
		long amount = 0l;
		for (UserTree tree: userTreeList) {
			amount += tree.getNumOfStorageFruits() * tree.getTreeType().getCostOfFruit();
			tree.setNumOfStorageFruits(0);
			userTreeRepository.save(tree);
		}
		
		userStats.setBalanceForBuyings(userStats.getBalanceForBuyings() + amount / 2);
		userStats.setBalanceForWithdrawal(userStats.getBalanceForWithdrawal() + amount / 2);
		userStatisticsService.saveStats(userStats);
	}

	public List<TreeType> getAllTrees() {

		List<TreeType> allTrees = treeTypeRepository.findAll();
		
		return allTrees;
	}
	
	
	@Transactional
	@Scheduled(fixedRateString  = "${tg.gameplay.update-time-milliseconds}")
	@Async
    public void printMessage() {
		
        logger.info("Update trees scheduled job is running...");
        
        List<UserTree> allUserTrees = userTreeRepository.findAll();
        
        for (UserTree userTree: allUserTrees) {
        	userTree.setNumOfTreeFruits(userTree.getNumOfTreeFruits() + userTree.getQuantity() * userTree.getTreeType().getHarvest());
        	userTreeRepository.save(userTree);
        }
        
        
    }
	
	
}
