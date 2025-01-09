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

import com.pijus.loanmgmtsystem.model.LoanType;
import com.pijus.loanmgmtsystem.services.LoanTypeService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/loan-mgmt-system")
public class LoanTypeController {

	@Autowired
	private LoanTypeService loanTypeService;

	@PostMapping("/loan-types")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LoanType> createLoanType(@RequestBody LoanType loanType) {
		LoanType type = null;
		try {
			if(loanType.getType()!=null && loanType.getDescription()!=null) {
				type = this.loanTypeService.addLoanType(loanType);
				return ResponseEntity.ok(type);
			}
			return ResponseEntity.ok(type);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/loan-types")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<LoanType>> getAllLoanTypes() {
		List<LoanType> list = this.loanTypeService.getAllLoanTypes();
		if (list.size() == 0) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.of(Optional.of(list));
	}

	@GetMapping("/loan-types/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Optional<LoanType>> getLoanTypeById(@PathVariable("id") String id) {
		Optional<LoanType> loanType = this.loanTypeService.getLoanTypeById(id);
		if (loanType == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(loanType));

	}

	@PutMapping("/loan-types/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LoanType> updateLoanType(@PathVariable("id") String id,
			@RequestBody LoanType loanTypeDetails) {

		try {
			LoanType updatedLoanType = this.loanTypeService.updateLoanType(id, loanTypeDetails);
			return ResponseEntity.ok(updatedLoanType);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/loan-types/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteLoanType(@PathVariable("id") String id) {

		try {
			Map<String, Boolean> response = this.loanTypeService.deleteLoanType(id);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
