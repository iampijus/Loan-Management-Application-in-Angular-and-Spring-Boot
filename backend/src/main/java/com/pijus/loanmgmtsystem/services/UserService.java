package com.pijus.loanmgmtsystem.services;


import com.pijus.loanmgmtsystem.payload.request.RegisterRequest;

public interface UserService {
	
	public void createNewUser(RegisterRequest registerRequest);
	
	Boolean existsByEmail(String email);
	

}
