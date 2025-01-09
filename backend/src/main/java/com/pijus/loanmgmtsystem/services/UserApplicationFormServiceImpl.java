package com.pijus.loanmgmtsystem.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pijus.loanmgmtsystem.helper.FileUploadHelper;
import com.pijus.loanmgmtsystem.model.UserApplicationForm;
import com.pijus.loanmgmtsystem.repository.UserApplicationFormRepository;

@Service
public class UserApplicationFormServiceImpl implements UserApplicationFormService {

	@Autowired
	private UserApplicationFormRepository userApplicationFormRepository;

	@Autowired
	private FileUploadHelper fileUploadHelper;

	@Override
	public Map<String, String> newApplication(String type, UserApplicationForm userApplicationForm,
			MultipartFile file) {

		// handling user document

		try {

			if (file.isEmpty()) {
				Map<String, String> response = new HashMap<>();
				response.put("status", "PAN is required");

				return response;
			}

			// file upload...

			boolean isUploaded = fileUploadHelper.uploadFile(file);
			if (isUploaded) {
				System.out.println("File uploaded successfully");

				// set the file name in the database
//				userApplicationForm.setDocument(file.getOriginalFilename());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// setting the user loan type, status and credit score
		userApplicationForm.getLoanDetails().setType(type);
		userApplicationForm.setStatus("Pending");
		userApplicationForm.setScore(0);

		userApplicationFormRepository.save(userApplicationForm);
		Map<String, String> response = new HashMap<>();
		response.put("status", "submitted");
		return response;
	}

	@Override
	public List<UserApplicationForm> getAllLoanApplication() {
		return userApplicationFormRepository.findAll();
	}

	@Override
	public Optional<UserApplicationForm> getLoanApplicationById(String id) {
		return userApplicationFormRepository.findById(id);
	}

	@Override
	public void updateLoanApplicationStatus(String id, String status) {
		UserApplicationForm applicationForm = userApplicationFormRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Application form does not exist with this id" + id));

		applicationForm.setStatus(status);
		userApplicationFormRepository.save(applicationForm);
	}

	@Override
	public void setCreditScore(String id, int score) {
		UserApplicationForm applicationForm = this.userApplicationFormRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Application form does not exist with this id" + id));

		applicationForm.setScore(score);
		userApplicationFormRepository.save(applicationForm);

	}

	@Override
	public List<UserApplicationForm> getLoanApplicationsByEmail(String email) {

		return userApplicationFormRepository.findByEmail(email);
	} 

}
