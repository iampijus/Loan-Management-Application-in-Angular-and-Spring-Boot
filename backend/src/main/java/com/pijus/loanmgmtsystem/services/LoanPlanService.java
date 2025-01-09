package com.pijus.loanmgmtsystem.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.pijus.loanmgmtsystem.model.LoanPlan;

public interface LoanPlanService {
	
	public LoanPlan addLoanPlan(LoanPlan plan);

	public List<LoanPlan> getAllLoanPlans();

	public Optional<LoanPlan> getLoanPlanById(String id);

	public LoanPlan updateLoanPlan(String id, LoanPlan plan);

	public Map<String, Boolean> deleteLoanPlan(String id);

}
