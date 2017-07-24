package org.crama.tropicalgarden.games;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Lottery implements Serializable {

	private static final long serialVersionUID = -2928320594604579722L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@Column(nullable = false)
	private double bank;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private LotteryTicket firstPlace;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private LotteryTicket secondPlace;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private LotteryTicket thirdPlace;
	
	@Column(nullable = false)
	private LotteryStatus lotteryStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getBank() {
		return bank;
	}

	public void setBank(double bank) {
		this.bank = bank;
	}

	public LotteryTicket getFirstPlace() {
		return firstPlace;
	}

	public void setFirstPlace(LotteryTicket firstPlace) {
		this.firstPlace = firstPlace;
	}

	public LotteryTicket getSecondPlace() {
		return secondPlace;
	}

	public void setSecondPlace(LotteryTicket secondPlace) {
		this.secondPlace = secondPlace;
	}

	public LotteryTicket getThirdPlace() {
		return thirdPlace;
	}

	public void setThirdPlace(LotteryTicket thirdPlace) {
		this.thirdPlace = thirdPlace;
	}

	public LotteryStatus getLotteryStatus() {
		return lotteryStatus;
	}

	public void setLotteryStatus(LotteryStatus lotteryStatus) {
		this.lotteryStatus = lotteryStatus;
	}
	
	
	
}
