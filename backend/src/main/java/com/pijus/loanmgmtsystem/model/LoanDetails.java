package com.pijus.loanmgmtsystem.model;

public class LoanDetails {

	private double amount;
	private String type;
	private String plan;

	public LoanDetails() {

	}

	public LoanDetails(double amount, String type, String plan) {
		super();
		this.amount = amount;
		this.type = type;
		this.plan = plan;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

}
