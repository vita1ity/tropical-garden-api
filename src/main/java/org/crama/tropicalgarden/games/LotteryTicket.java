package org.crama.tropicalgarden.games;

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
import javax.persistence.ManyToOne;

import org.crama.tropicalgarden.users.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LotteryTicket implements Serializable {

	private static final long serialVersionUID = 4209943193396942007L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(nullable = false)
	private long ticketNumber;
	
	@Column(nullable = false)
	private LocalDateTime ticketBoughtTime;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "lottery_id", nullable = false)
	private Lottery lottery;
	
	private long wonAmount;

	public LotteryTicket() {
		super();
	}

	public LotteryTicket(User user, long ticketNumber, LocalDateTime ticketBoughtTime, Lottery lottery) {
		super();
		this.user = user;
		this.ticketNumber = ticketNumber;
		this.ticketBoughtTime = ticketBoughtTime;
		this.lottery = lottery;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Lottery getLottery() {
		return lottery;
	}

	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

	public long getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public LocalDateTime getTicketBoughtTime() {
		return ticketBoughtTime;
	}

	public void setTicketBoughtTime(LocalDateTime ticketBoughtTime) {
		this.ticketBoughtTime = ticketBoughtTime;
	}

	public long getWonAmount() {
		return wonAmount;
	}

	public void setWonAmount(long wonAmount) {
		this.wonAmount = wonAmount;
	}
	
}
