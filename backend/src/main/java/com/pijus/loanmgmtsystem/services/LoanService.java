package com.pijus.loanmgmtsystem.services;

import java.util.List;

import com.pijus.loanmgmtsystem.model.Loan;
import com.pijus.loanmgmtsystem.model.LoanDataRequest;

public interface LoanService {

	public void saveLoanDetails(LoanDataRequest loanData);
	
	public List<Loan> getAllLoanDetails();
	
	void updateLoanStatus(String id, String status);

	public void deleteLoanDetailsById(String id);

}
