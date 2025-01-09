package com.pijus.loanmgmtsystem.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pijus.loanmgmtsystem.model.LoanType;
import com.pijus.loanmgmtsystem.repository.LoanTypeRepository;

@Service
public class LoanTypeServiceImpl implements LoanTypeService {
	
	@Autowired
	LoanTypeRepository loanTypeRepository;
	

	@Override
	public LoanType addLoanType(LoanType type) {
		
		return loanTypeRepository.save(type);
	}
 
	@Override
	public List<LoanType> getAllLoanTypes() {
		return loanTypeRepository.findAll();
	}

	@Override
	public Optional<LoanType> getLoanTypeById(String id) {
		return loanTypeRepository.findById(id);
	}

	@Override
	public LoanType updateLoanType(String id, LoanType type) {
		LoanType loanType = loanTypeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan type does not exist with this id" + id));

		loanType.setType(type.getType());
		loanType.setDescription(type.getDescription());

		LoanType updatedLoanType=loanTypeRepository.save(loanType);
		return updatedLoanType;
	}

	@Override
	public Map<String, Boolean> deleteLoanType(String id) {
		
		
		LoanType loanType=null;
		loanType=loanTypeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan type does not exist with this id" + id));
		
		loanTypeRepository.delete(loanType);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", true);
     	return response;
		
	}

}
