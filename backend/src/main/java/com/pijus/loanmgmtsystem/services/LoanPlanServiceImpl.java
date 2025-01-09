package com.pijus.loanmgmtsystem.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pijus.loanmgmtsystem.model.LoanPlan;
import com.pijus.loanmgmtsystem.repository.LoanPlanRepository;

@Service
public class LoanPlanServiceImpl implements LoanPlanService{
	@Autowired
	private LoanPlanRepository loanPlanRepository;

	// add loan plan
    @Override
	public LoanPlan addLoanPlan(LoanPlan plan) {
		return loanPlanRepository.save(plan);
	}

	// get all loan plans
    @Override
	public List<LoanPlan> getAllLoanPlans() {
		return loanPlanRepository.findAll();
	}

	// get loan plan by id
    @Override
	public Optional<LoanPlan> getLoanPlanById(String id) {
		return loanPlanRepository.findById(id);
	}

	// update loan plan
    @Override
	public LoanPlan updateLoanPlan(String id, LoanPlan plan) {

		LoanPlan loanPlan = loanPlanRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan plan does not exist with this id" + id));

		loanPlan.setPlan(plan.getPlan());
		loanPlan.setInterest(plan.getInterest());
		loanPlan.setPenalty(plan.getPenalty());

		LoanPlan updatedLoanPlan = loanPlanRepository.save(loanPlan);
		return updatedLoanPlan;
	}

	// delete loan plan
    @Override
	public Map<String, Boolean> deleteLoanPlan(String id) {
		LoanPlan loanPlan = null;
		loanPlan = loanPlanRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan plan does not exist with this id" + id));

		loanPlanRepository.delete(loanPlan);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", true);
		return response;
	}
}
