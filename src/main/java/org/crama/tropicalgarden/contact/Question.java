package org.crama.tropicalgarden.contact;

import java.io.Serializable;

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
public class Question implements Serializable {

	private static final long serialVersionUID = -6186635711245110998L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	
	@Column(nullable = false)
	private String questionTheme;

	@Column(nullable = false)
	private String text;
	
	//if null use email from user object
	@Column(nullable = true)
	private String callBackEmail;

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

	public String getQuestionTheme() {
		return questionTheme;
	}

	public void setQuestionTheme(String questionTheme) {
		this.questionTheme = questionTheme;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCallBackEmail() {
		return callBackEmail;
	}

	public void setCallBackEmail(String callBackEmail) {
		this.callBackEmail = callBackEmail;
	}
	
}

