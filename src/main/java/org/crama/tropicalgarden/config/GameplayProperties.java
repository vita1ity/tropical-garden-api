package org.crama.tropicalgarden.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("tg.gameplay")
@Component
public class GameplayProperties {

	//private long balanceForBuying = 1000;
	private long balanceForBuying; 
	
	private int updateTimeMin = 10;
	
	private long updateTimeMilliseconds = 600000;
	

	public long getBalanceForBuying() {
		return balanceForBuying;
	}

	public void setBalanceForBuying(long balanceForBuying) {
		this.balanceForBuying = balanceForBuying;
	}

	public int getUpdateTimeMin() {
		return updateTimeMin;
	}

	public void setUpdateTimeMin(int updateTimeMin) {
		this.updateTimeMin = updateTimeMin;
	}

	public long getUpdateTimeMilliseconds() {
		return updateTimeMilliseconds;
	}

	public void setUpdateTimeMilliseconds(long updateTimeMilliseconds) {
		this.updateTimeMilliseconds = updateTimeMilliseconds;
	}
	
	
	
	
}
