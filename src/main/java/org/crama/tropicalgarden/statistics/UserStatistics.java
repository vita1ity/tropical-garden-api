package org.crama.tropicalgarden.statistics;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.crama.tropicalgarden.users.User;

@Entity
public class UserStatistics implements Serializable {

	private static final long serialVersionUID = -5205715654773455578L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID", nullable=false)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY) 
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	
	private LocalDateTime registrationDate;
	
	private long coinsEarnedTotally;
	
	private long coinsEarnedInGame;
	
	private long coinsEarnedBySurfing;
	
	private long coinsEarnedByRefferals;
	
	private double investments;
	
	private double withdrawals;
	
	private long balanceForBuyings;
	
	private long balanceForWithdrawal;
	
	private int numOfPartners;
	
	private boolean madeDeposit;
	
	private int numOfRouletteTickets;
	
	private int transitionsToPartnerLink;
	
	private long coinsEarnedOnTransitions;

	private LocalDateTime lastFruitCollection;
	
	public UserStatistics() {
		super();
	}
	

	public UserStatistics( User user, LocalDateTime registrationDate, long balanceForBuyings) {
		super();
		this.user = user;
		this.registrationDate = registrationDate;
		this.balanceForBuyings = balanceForBuyings;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public long getCoinsEarnedTotally() {
		return coinsEarnedTotally;
	}

	public void setCoinsEarnedTotally(long coinsEarnedTotally) {
		this.coinsEarnedTotally = coinsEarnedTotally;
	}

	public long getCoinsEarnedInGame() {
		return coinsEarnedInGame;
	}

	public void setCoinsEarnedInGame(long coinsEarnedInGame) {
		this.coinsEarnedInGame = coinsEarnedInGame;
	}

	public long getCoinsEarnedBySurfing() {
		return coinsEarnedBySurfing;
	}

	public void setCoinsEarnedBySurfing(long coinsEarnedBySurfing) {
		this.coinsEarnedBySurfing = coinsEarnedBySurfing;
	}

	public long getCoinsEarnedByRefferals() {
		return coinsEarnedByRefferals;
	}

	public void setCoinsEarnedByRefferals(long coinsEarnedByRefferals) {
		this.coinsEarnedByRefferals = coinsEarnedByRefferals;
	}

	public double getInvestments() {
		return investments;
	}

	public void setInvestments(double investments) {
		this.investments = investments;
	}

	public double getWithdrawals() {
		return withdrawals;
	}

	public void setWithdrawals(double withdrawals) {
		this.withdrawals = withdrawals;
	}

	public long getBalanceForBuyings() {
		return balanceForBuyings;
	}

	public void setBalanceForBuyings(long balanceForBuyings) {
		this.balanceForBuyings = balanceForBuyings;
	}

	public long getBalanceForWithdrawal() {
		return balanceForWithdrawal;
	}

	public void setBalanceForWithdrawal(long balanceForWithdrawal) {
		this.balanceForWithdrawal = balanceForWithdrawal;
	}

	public int getNumOfPartners() {
		return numOfPartners;
	}

	public void setNumOfPartners(int numOfPartners) {
		this.numOfPartners = numOfPartners;
	}

	public boolean isMadeDeposit() {
		return madeDeposit;
	}

	public void setMadeDeposit(boolean madeDeposit) {
		this.madeDeposit = madeDeposit;
	}

	public int getNumOfRouletteTickets() {
		return numOfRouletteTickets;
	}

	public void setNumOfRouletteTickets(int numOfRouletteTickets) {
		this.numOfRouletteTickets = numOfRouletteTickets;
	}

	public int getTransitionsToPartnerLink() {
		return transitionsToPartnerLink;
	}

	public void setTransitionsToPartnerLink(int transitionsToPartnerLink) {
		this.transitionsToPartnerLink = transitionsToPartnerLink;
	}

	public long getCoinsEarnedOnTransitions() {
		return coinsEarnedOnTransitions;
	}

	public void setCoinsEarnedOnTransitions(long coinsEarnedOnTransitions) {
		this.coinsEarnedOnTransitions = coinsEarnedOnTransitions;
	}


	public LocalDateTime getLastFruitCollection() {
		return lastFruitCollection;
	}


	public void setLastFruitCollection(LocalDateTime lastFruitCollection) {
		this.lastFruitCollection = lastFruitCollection;
	}
	
	
	
}
