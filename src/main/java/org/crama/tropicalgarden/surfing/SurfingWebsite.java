package org.crama.tropicalgarden.surfing;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SurfingWebsite implements Serializable {

	private static final long serialVersionUID = 2451667140964475939L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	private long id;
	
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY) 
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(nullable = false)
	private String siteName;
	
	private String siteDescription;
	
	@Column(nullable = false)
	private String siteUrl;
	
	@Column(nullable = false)
	private long pointsForView;
	
	@Column(nullable = false)
	private int viewTime;
	
	private boolean isHighlighted;
	
	private boolean actuallyRedirect;
	
	private long surfingBudget;
	
	private int viewsAvailable;
	
	private int numOfViews;

	private WebsiteStatus status;
	
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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteDescription() {
		return siteDescription;
	}

	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public long getPointsForView() {
		return pointsForView;
	}

	public void setPointsForView(long pointsForView) {
		this.pointsForView = pointsForView;
	}

	public int getViewTime() {
		return viewTime;
	}

	public void setViewTime(int viewTime) {
		this.viewTime = viewTime;
	}

	public boolean getIsHighlighted() {
		return isHighlighted;
	}

	public void setIsHighlighted(boolean isHighlighted) {
		this.isHighlighted = isHighlighted;
	}

	public boolean getActuallyRedirect() {
		return actuallyRedirect;
	}

	public void setActuallyRedirect(boolean actuallyRedirect) {
		this.actuallyRedirect = actuallyRedirect;
	}

	public long getSurfingBudget() {
		return surfingBudget;
	}

	public void setSurfingBudget(long surfingBudget) {
		this.surfingBudget = surfingBudget;
	}

	public int getViewsAvailable() {
		return viewsAvailable;
	}

	public void setViewsAvailable(int viewsAvailable) {
		this.viewsAvailable = viewsAvailable;
	}

	public int getNumOfViews() {
		return numOfViews;
	}

	public void setNumOfViews(int numOfViews) {
		this.numOfViews = numOfViews;
	}

	public WebsiteStatus getStatus() {
		return status;
	}

	public void setStatus(WebsiteStatus status) {
		this.status = status;
	}
	
	
	
}
