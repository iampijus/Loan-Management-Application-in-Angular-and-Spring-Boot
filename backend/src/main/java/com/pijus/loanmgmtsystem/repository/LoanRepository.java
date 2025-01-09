package com.pijus.loanmgmtsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pijus.loanmgmtsystem.model.Loan;

@Repository
public interface LoanRepository extends MongoRepository<Loan,String> {

}
