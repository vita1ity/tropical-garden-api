package org.crama.tropicalgarden.garden;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TreeType {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private long price;
	
	//number of fruits in 10 minutes
	@Column(nullable = false)
	private int harvest;
	
	@Column(nullable = false)
	private int costOfFruit;
	
	private String information;
	
	private String infoPictureUrl;
	
	private String iconUrl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getHarvest() {
		return harvest;
	}

	public void setHarvest(int harvest) {
		this.harvest = harvest;
	}

	public int getCostOfFruit() {
		return costOfFruit;
	}

	public void setCostOfFruit(int costOfFruit) {
		this.costOfFruit = costOfFruit;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getInfoPictureUrl() {
		return infoPictureUrl;
	}

	public void setInfoPictureUrl(String infoPictureUrl) {
		this.infoPictureUrl = infoPictureUrl;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	@Override
	public String toString() {
		return "TreeType [id=" + id + ", name=" + name + ", price=" + price + ", harvest=" + harvest + ", costOfFruit="
				+ costOfFruit + ", information=" + information + ", infoPictureUrl=" + infoPictureUrl + ", iconUrl="
				+ iconUrl + "]";
	}
	
}
