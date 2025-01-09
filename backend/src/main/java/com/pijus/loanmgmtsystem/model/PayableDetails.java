package com.pijus.loanmgmtsystem.model;


public class PayableDetails {
	private double payable;
	private double penalty;
	private String date;

	public PayableDetails() {

	}

	public PayableDetails(double payable, double penalty, String date) {
		super();
		this.payable = payable;
		this.penalty = penalty;
		this.date = date;
	}

	public double getPayable() {
		return payable;
	}

	public void setPayable(double payable) {
		this.payable = payable;
	}

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
