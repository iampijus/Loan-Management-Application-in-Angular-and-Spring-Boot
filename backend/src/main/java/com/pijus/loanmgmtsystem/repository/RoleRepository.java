package com.pijus.loanmgmtsystem.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pijus.loanmgmtsystem.model.ERole;
import com.pijus.loanmgmtsystem.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}
