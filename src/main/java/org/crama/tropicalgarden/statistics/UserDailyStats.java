package org.crama.tropicalgarden.statistics;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.crama.tropicalgarden.users.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserDailyStats implements Serializable {

	private static final long serialVersionUID = -8082206245232188228L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	private int id;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY) 
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	private LocalDate date;
	
	private long coinsEarnedTotally;
	
	private long coinsEarnedBySurfing;
	
	private long coinsEarnedInGame;
	
	private long coinsEarnedByRefferals;
	
	private double investments;
	
	private double withdrawals;
	
	private boolean wasOnline;
	
	private int transitionsToPartnerLink;

	public UserDailyStats(User user, LocalDate date) {
		this.user = user;
		this.date = date;
	}
	
	public UserDailyStats() {
		super();
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public long getCoinsEarnedTotally() {
		return coinsEarnedTotally;
	}

	public void setCoinsEarnedTotally(long coinsEarnedTotally) {
		this.coinsEarnedTotally = coinsEarnedTotally;
	}

	public long getCoinsEarnedBySurfing() {
		return coinsEarnedBySurfing;
	}

	public void setCoinsEarnedBySurfing(long coinsEarnedBySurfing) {
		this.coinsEarnedBySurfing = coinsEarnedBySurfing;
	}

	public long getCoinsEarnedInGame() {
		return coinsEarnedInGame;
	}

	public void setCoinsEarnedInGame(long coinsEarnedInGame) {
		this.coinsEarnedInGame = coinsEarnedInGame;
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

	public boolean isWasOnline() {
		return wasOnline;
	}

	public void setWasOnline(boolean wasOnline) {
		this.wasOnline = wasOnline;
	}

	public int getTransitionsToPartnerLink() {
		return transitionsToPartnerLink;
	}

	public void setTransitionsToPartnerLink(int transitionsToPartnerLink) {
		this.transitionsToPartnerLink = transitionsToPartnerLink;
	}
	
	
	
}
