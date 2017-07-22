package org.crama.tropicalgarden.contests;

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

@Entity
public class ReferralContest implements Serializable {

	private static final long serialVersionUID = 3683346458388218284L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private LocalDate startDate;
	
	@Column(nullable = false)
	private LocalDate endDate; 
	
	@Column(nullable = false)
	private double firstPlcePrize;
	
	@Column(nullable = false)
	private double secondPlcePrize;
	
	@Column(nullable = false)
	private double thirdPlcePrize;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "first_place_user", nullable = true)
	private User firstPlaceUser;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "second_place_user", nullable = true)
	private User secondPlaceUser;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "third_place_user", nullable = true)
	private User thirdPlaceUser;
	
	@Column(nullable = false)
	private ReferralContestStatus contestStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getFirstPlcePrize() {
		return firstPlcePrize;
	}

	public void setFirstPlcePrize(double firstPlcePrize) {
		this.firstPlcePrize = firstPlcePrize;
	}

	public double getSecondPlcePrize() {
		return secondPlcePrize;
	}

	public void setSecondPlcePrize(double secondPlcePrize) {
		this.secondPlcePrize = secondPlcePrize;
	}

	public double getThirdPlcePrize() {
		return thirdPlcePrize;
	}

	public void setThirdPlcePrize(double thirdPlcePrize) {
		this.thirdPlcePrize = thirdPlcePrize;
	}

	public User getFirstPlaceUser() {
		return firstPlaceUser;
	}

	public void setFirstPlaceUser(User firstPlaceUser) {
		this.firstPlaceUser = firstPlaceUser;
	}

	public User getSecondPlaceUser() {
		return secondPlaceUser;
	}

	public void setSecondPlaceUser(User secondPlaceUser) {
		this.secondPlaceUser = secondPlaceUser;
	}

	public User getThirdPlaceUser() {
		return thirdPlaceUser;
	}

	public void setThirdPlaceUser(User thirdPlaceUser) {
		this.thirdPlaceUser = thirdPlaceUser;
	}

	public ReferralContestStatus getContestStatus() {
		return contestStatus;
	}

	public void setContestStatus(ReferralContestStatus contestStatus) {
		this.contestStatus = contestStatus;
	} 
	
	
	
	
}
