package com.pijus.loanmgmtsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pijus.loanmgmtsystem.model.LoanType;

@Repository
public interface LoanTypeRepository extends MongoRepository<LoanType, String> {

}
