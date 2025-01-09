package com.pijus.loanmgmtsystem.model;

public class LoanDataRequest {
	private String id;
	private double totalAmount;
	private double penaltyAmount;
	private int duration;
	private String loanStatus;

	public LoanDataRequest() {

	}

	
	public LoanDataRequest(String id, double totalAmount, double penaltyAmount, int duration, String loanStatus) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
		this.penaltyAmount = penaltyAmount;
		this.duration = duration;
		this.loanStatus = loanStatus;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(double penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}
	
	

	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}


	@Override
	public String toString() {
		return "LoanDataRequest [id=" + id + ", totalAmount=" + totalAmount + ", penaltyAmount=" + penaltyAmount
				+ ", duration=" + duration + ", loanStatus=" + loanStatus + "]";
	}

	
	
	

}
