package com.pijus.loanmgmtsystem.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.pijus.loanmgmtsystem.model.UserApplicationForm;

public interface UserApplicationFormService {

	public Map<String, String> newApplication(String type, UserApplicationForm userApplicationForm, MultipartFile file);

	public List<UserApplicationForm> getAllLoanApplication();

	public Optional<UserApplicationForm> getLoanApplicationById(String id);

	public List<UserApplicationForm> getLoanApplicationsByEmail(String email);
	
	public void updateLoanApplicationStatus(String id, String status);

	public void setCreditScore(String id, int score);

}
