package org.crama.tropicalgarden.finance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentSystem implements Serializable {

	private static final long serialVersionUID = -2861531503245521072L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String paymentSystem;
	
	@Column(nullable = true)
	private String icon;
	
	@Column(nullable = false)
	private double commission;
	
	@Column(nullable = false)
	private PaymentSystemCommissionType commissionType;  
	
	@Column(nullable = false)
	private double systemCommission;
	
	@Column(nullable = false)
	private double withdrawalMinLimit;
	
	@Column(nullable = false)
	private double withdrawalMaxLimit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPaymentSystem() {
		return paymentSystem;
	}

	public void setPaymentSystem(String paymentSystem) {
		this.paymentSystem = paymentSystem;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public PaymentSystemCommissionType getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(PaymentSystemCommissionType commissionType) {
		this.commissionType = commissionType;
	}

	public double getSystemCommission() {
		return systemCommission;
	}

	public void setSystemCommission(double systemCommission) {
		this.systemCommission = systemCommission;
	}

	public double getWithdrawalMinLimit() {
		return withdrawalMinLimit;
	}

	public void setWithdrawalMinLimit(double withdrawalMinLimit) {
		this.withdrawalMinLimit = withdrawalMinLimit;
	}

	public double getWithdrawalMaxLimit() {
		return withdrawalMaxLimit;
	}

	public void setWithdrawalMaxLimit(double withdrawalMaxLimit) {
		this.withdrawalMaxLimit = withdrawalMaxLimit;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
}
