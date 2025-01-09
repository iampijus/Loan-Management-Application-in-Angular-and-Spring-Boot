package com.pijus.loanmgmtsystem.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pijus.loanmgmtsystem.model.Loan;
import com.pijus.loanmgmtsystem.model.LoanDataRequest;
import com.pijus.loanmgmtsystem.model.LoanDetails;
import com.pijus.loanmgmtsystem.model.PayableDetails;
import com.pijus.loanmgmtsystem.model.UserApplicationForm;
import com.pijus.loanmgmtsystem.repository.LoanRepository;
import com.pijus.loanmgmtsystem.repository.UserApplicationFormRepository;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private UserApplicationFormRepository userApplicationFormRepository;

	@Override
	public void saveLoanDetails(LoanDataRequest loanData) {

		Loan loan = new Loan();

		String loanId = loanData.getId();
		int loanDuration = loanData.getDuration();
		// For setting the loan duration as string in the LoanDetails
		String loanDurationAsString = Integer.toString(loanDuration);

		double totalAmount = loanData.getTotalAmount();
		double penaltyAmount = loanData.getPenaltyAmount();
		String loanStatus = loanData.getLoanStatus();

		// Get the current data
		Date currentDate = new Date();
		// Format the date as a string
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentDate);

		Optional<UserApplicationForm> applicationFormOptional = userApplicationFormRepository.findById(loanId);

		if (applicationFormOptional.isPresent()) {
			UserApplicationForm applicationForm = applicationFormOptional.get();

			// set the properties of loan from loan application
			loan.setName(applicationForm.getName());
			loan.setEmail(applicationForm.getEmail());
			loan.setContact(applicationForm.getContact());
			loan.setAddress(applicationForm.getAddress());

			// set the loanDetails properties
			// Initialize LoanDetails if it's null
			if (loan.getLoanDetails() == null) {
				loan.setLoanDetails(new LoanDetails());
			}

			loan.getLoanDetails().setAmount(applicationForm.getLoanDetails().getAmount());
			loan.getLoanDetails().setType(applicationForm.getLoanDetails().getType());
			loan.getLoanDetails().setPlan(loanDurationAsString);

			// Initialize PayableDetails if it's null
			if (loan.getPayableDetails() == null) {
				loan.setPayableDetails(new PayableDetails());
			}
			loan.getPayableDetails().setPayable(totalAmount);
			loan.getPayableDetails().setPenalty(penaltyAmount);
			loan.getPayableDetails().setDate(dateString);
			loan.setStatus(loanStatus);

			loanRepository.save(loan);

		} else {
			System.out.println("Application form is not exist");
		}

	}

	@Override
	public List<Loan> getAllLoanDetails() {
		return loanRepository.findAll();
	}

	@Override
	public void deleteLoanDetailsById(String id) {
		Loan loan = null;
		loan = loanRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan does not exist with this id" + id));

		loanRepository.delete(loan);

	}

	@Override
	public void updateLoanStatus(String id, String status) {

		Loan loan = null;
		loan = loanRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan does not exist with this id" + id));

		// Get the current data
		Date currentDate = new Date();
		// Format the date as a string
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentDate);

		// change the loan status
		loan.setStatus(status);

		// change the date
		loan.getPayableDetails().setDate(dateString);

		loanRepository.save(loan);
	}

}
