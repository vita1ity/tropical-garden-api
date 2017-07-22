package org.crama.tropicalgarden.gamestats;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameStatistics implements Serializable {

	private static final long serialVersionUID = -3821430358162103545L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private double totalBalance;
	
	@Column(nullable = false)
	private double balanceWithdrawn;
	
	@Column(nullable = false)
	private long usersRegistered;
	
	@Column(nullable = false)
	private long usersOnline;
	
	@Column(nullable = false)
	private int projectLiveDays;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public double getBalanceWithdrawn() {
		return balanceWithdrawn;
	}

	public void setBalanceWithdrawn(double balanceWithdrawn) {
		this.balanceWithdrawn = balanceWithdrawn;
	}

	public long getUsersRegistered() {
		return usersRegistered;
	}

	public void setUsersRegistered(long usersRegistered) {
		this.usersRegistered = usersRegistered;
	}

	public long getUsersOnline() {
		return usersOnline;
	}

	public void setUsersOnline(long usersOnline) {
		this.usersOnline = usersOnline;
	}

	public int getProjectLiveDays() {
		return projectLiveDays;
	}

	public void setProjectLiveDays(int projectLiveDays) {
		this.projectLiveDays = projectLiveDays;
	}
	
	
}
