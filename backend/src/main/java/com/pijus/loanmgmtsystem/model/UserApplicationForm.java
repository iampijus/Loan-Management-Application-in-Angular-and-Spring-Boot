package com.pijus.loanmgmtsystem.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document("application_forms")
public class UserApplicationForm {

	@Id
	private String id;
	private String name;
	private String email;
	private long contact;
	private Address address;
	private LoanDetails loanDetails;
	private String status;
	private int score;
	private String document;

	public UserApplicationForm() {

	}

	public UserApplicationForm(String id, String name, String email, long contact, Address address,
			LoanDetails loanDetails, String status, int score, String document) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.loanDetails = loanDetails;
		this.status = status;
		this.score = score;
		this.document = document;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LoanDetails getLoanDetails() {
		return loanDetails;
	}

	public void setLoanDetails(LoanDetails loanDetails) {
		this.loanDetails = loanDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "UserApplicationForm [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact
				+ ", address=" + address + ", loanDetails=" + loanDetails + ", status=" + status + ", score=" + score
				+ ", document=" + document + "]";
	}

}
