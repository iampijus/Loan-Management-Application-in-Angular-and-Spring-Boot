package com.pijus.loanmgmtsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("loan_types")
public class LoanType {

	@Id
	private String id;
	private String type;
	private String description;

	public LoanType() {

	}

	public LoanType(String id, String type, String description) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
