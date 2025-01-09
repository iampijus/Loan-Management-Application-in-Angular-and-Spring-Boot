package com.pijus.loanmgmtsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pijus.loanmgmtsystem.model.LoanPlan;

@Repository
public interface LoanPlanRepository extends MongoRepository<LoanPlan, String> {

}
