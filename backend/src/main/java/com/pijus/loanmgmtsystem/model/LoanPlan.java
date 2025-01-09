package com.pijus.loanmgmtsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("loan_plans")
public class LoanPlan {
	
	@Id
	private String id;
	private int plan;
	private int interest;
	private int penalty;

	public LoanPlan() {

	}

	public LoanPlan(String id, int plan, int interest, int penalty) {
		super();
		this.id = id;
		this.plan = plan;
		this.interest = interest;
		this.penalty = penalty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPlan() {
		return plan;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
}
