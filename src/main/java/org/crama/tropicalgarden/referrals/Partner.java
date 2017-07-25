package org.crama.tropicalgarden.referrals;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.crama.tropicalgarden.users.User;

@Entity
public class Partner implements Serializable {

	private static final long serialVersionUID = 613018463465576446L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//main user
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	//main user partner
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "partner_id", nullable = false)
	private User partner;

	public Partner(User user, User partner) {
		super();
		this.user = user;
		this.partner = partner;
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

	public User getPartner() {
		return partner;
	}

	public void setPartner(User partner) {
		this.partner = partner;
	}
	
	
	
}
