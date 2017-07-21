package org.crama.tropicalgarden.garden;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserTree {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private long id;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY) 
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY) 
	@JoinColumn(name = "tree_id", nullable = false)
	private TreeType treeType;
	
	@Column(nullable = false)
	private int quantity;
	
	private int numOfTreeFruits;
	
	private int numOfStorageFruits;

	
	public UserTree() {
		super();
	}

	public UserTree(User user, TreeType treeType, int quantity) {
		super();
		this.user = user;
		this.treeType = treeType;
		this.quantity = quantity;
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

	public TreeType getTreeType() {
		return treeType;
	}

	public void setTreeType(TreeType treeType) {
		this.treeType = treeType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getNumOfTreeFruits() {
		return numOfTreeFruits;
	}

	public void setNumOfTreeFruits(int numOfTreeFruits) {
		this.numOfTreeFruits = numOfTreeFruits;
	}

	public int getNumOfStorageFruits() {
		return numOfStorageFruits;
	}

	public void setNumOfStorageFruits(int numOfStorageFruits) {
		this.numOfStorageFruits = numOfStorageFruits;
	}
	
	
	
}
