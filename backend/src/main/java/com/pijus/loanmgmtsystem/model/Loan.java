package com.pijus.loanmgmtsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("loans")
public class Loan {
	
	@Id
	private String id;
	private String name;
	private String email;
	private long contact;
	private Address address;
	private LoanDetails loanDetails;
	private PayableDetails payableDetails;
	private String status;
	
	public Loan() {
		
	}

	public Loan(String id, String name, String email, long contact, Address address, LoanDetails loanDetails,
			PayableDetails payableDetails, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.loanDetails = loanDetails;
		this.payableDetails = payableDetails;
		this.status = status;
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

	public PayableDetails getPayableDetails() {
		return payableDetails;
	}

	public void setPayableDetails(PayableDetails payableDetails) {
		this.payableDetails = payableDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
