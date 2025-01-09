
package com.pijus.loanmgmtsystem.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.pijus.loanmgmtsystem.model.LoanPlan;
import com.pijus.loanmgmtsystem.services.LoanPlanService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/loan-mgmt-system")
public class LoanPlanController {

	@Autowired
	private LoanPlanService loanPlanService;

	// create loan plan
	@PostMapping("/loan-plans")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LoanPlan> createLoanPlan(@RequestBody LoanPlan loanPlan) {
		LoanPlan plan = null;
		try {
			if(loanPlan.getPlan()!=0 && loanPlan.getInterest()!=0 && loanPlan.getPenalty()!=0 ) {
				plan = this.loanPlanService.addLoanPlan(loanPlan);
				return ResponseEntity.ok(plan);
			}
			return ResponseEntity.ok(plan);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// get all loan plan
	@GetMapping("/loan-plans")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<LoanPlan>> getAllLoanPlans() {
		List<LoanPlan> list = this.loanPlanService.getAllLoanPlans();
		if (list.size() == 0) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.ok(list);
	}
	

	// get loan plan by id
	@GetMapping("/loan-plans/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Optional<LoanPlan>> getLoanPlanById(@PathVariable("id") String id) {
		Optional<LoanPlan> loanPlan = this.loanPlanService.getLoanPlanById(id);
		if (loanPlan == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(loanPlan));

	}

	// update loan plan
	@PutMapping("/loan-plans/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LoanPlan> updateLoanPlan(@PathVariable("id") String id,
			@RequestBody LoanPlan loanPlanDetails) {

		try {
			LoanPlan updatedLoanPlan = this.loanPlanService.updateLoanPlan(id, loanPlanDetails);
			return ResponseEntity.ok(updatedLoanPlan);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// delete loan plan
	@DeleteMapping("/loan-plans/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteLoanPlan(@PathVariable("id") String id) {

		try {
			Map<String, Boolean> response = this.loanPlanService.deleteLoanPlan(id);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
