package com.pijus.loanmgmtsystem.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pijus.loanmgmtsystem.model.UserApplicationForm;
import com.pijus.loanmgmtsystem.services.UserApplicationFormService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/loan-mgmt-system")
public class UserApplicationFormController {

	@Autowired
	private UserApplicationFormService userApplicationFormService;

	@PostMapping("/user-application/{type}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Map<String, String>> submitNewApplication(@PathVariable("type") String type,
			@RequestParam("otherData") String otherData, @RequestParam("document") MultipartFile file) {
		try {

			// convert otherDataString to an object
			ObjectMapper objectMapper = new ObjectMapper();
			UserApplicationForm userApplicationForm = objectMapper.readValue(otherData, UserApplicationForm.class);

			Map<String, String> response = this.userApplicationFormService.newApplication(type, userApplicationForm,
					file);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/user-application")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<UserApplicationForm>> getAllLoanApplication() {
		List<UserApplicationForm> list = this.userApplicationFormService.getAllLoanApplication();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/user-application/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Optional<UserApplicationForm>> getLoanApplicationById(@PathVariable("id") String id) {

		Optional<UserApplicationForm> userApplicationForm = this.userApplicationFormService.getLoanApplicationById(id);
		if (userApplicationForm == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(userApplicationForm); 

	}

	@PutMapping("/user-application/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void updateLoanStatus(@PathVariable("id") String id, @RequestBody String status) {
		this.userApplicationFormService.updateLoanApplicationStatus(id, status);
	}

	@PutMapping("/user-application/score/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void setCreditScore(@PathVariable("id") String id, @RequestBody int score) {
		this.userApplicationFormService.setCreditScore(id, score);
	}

	@PostMapping("/user-application/status")
	@PreAuthorize(" hasRole('USER')")
	public ResponseEntity<List<UserApplicationForm>> getLoanApplicationsByEmail(@RequestBody String email) {
		List<UserApplicationForm> list = this.userApplicationFormService.getLoanApplicationsByEmail(email);

		return ResponseEntity.ok(list);
	}

}
