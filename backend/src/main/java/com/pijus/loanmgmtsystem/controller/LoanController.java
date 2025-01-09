package com.pijus.loanmgmtsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pijus.loanmgmtsystem.model.Loan;
import com.pijus.loanmgmtsystem.model.LoanDataRequest;
import com.pijus.loanmgmtsystem.services.LoanService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/loan-mgmt-system")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@PostMapping("/loans")
	@PreAuthorize("hasRole('ADMIN')")
	public void saveLoanDetails(@RequestBody LoanDataRequest loanData) {

		try {

			this.loanService.saveLoanDetails(loanData);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@GetMapping("/loans")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Loan>> getAllLoanDetails() {
		List<Loan> list = this.loanService.getAllLoanDetails();
		return ResponseEntity.ok(list);
	}

	@PutMapping("/loans/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void updateLoanStatus(@PathVariable("id") String id, @RequestBody String status) {
		loanService.updateLoanStatus(id, status);
	}

	@DeleteMapping("/loans/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteLoanById(@PathVariable("id") String id) {

		loanService.deleteLoanDetailsById(id);

	}

}
