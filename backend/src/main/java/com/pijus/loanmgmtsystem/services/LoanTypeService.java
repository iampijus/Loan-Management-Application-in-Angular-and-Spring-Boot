package com.pijus.loanmgmtsystem.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.pijus.loanmgmtsystem.model.LoanType;

public interface LoanTypeService {
	
	public LoanType addLoanType(LoanType type);
	
	public List<LoanType> getAllLoanTypes();
	
	public Optional<LoanType> getLoanTypeById(String id);
	
	public LoanType updateLoanType(String id,LoanType type);
	
	public Map<String,Boolean> deleteLoanType(String id);

}
