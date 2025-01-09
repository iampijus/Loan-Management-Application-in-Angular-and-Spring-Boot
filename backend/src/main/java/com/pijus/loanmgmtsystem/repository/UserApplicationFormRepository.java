package com.pijus.loanmgmtsystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pijus.loanmgmtsystem.model.UserApplicationForm;

@Repository
public interface UserApplicationFormRepository extends MongoRepository<UserApplicationForm, String> {

	List<UserApplicationForm> findByEmail(String email);
}
