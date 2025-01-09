package com.pijus.loanmgmtsystem.controller;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pijus.loanmgmtsystem.model.LoginRequest;

import com.pijus.loanmgmtsystem.payload.request.RegisterRequest;
import com.pijus.loanmgmtsystem.payload.response.JwtResponse;
import com.pijus.loanmgmtsystem.payload.response.MessageResponse;

import com.pijus.loanmgmtsystem.security.jwt.JwtUtils;
import com.pijus.loanmgmtsystem.services.UserDetailsImpl;
import com.pijus.loanmgmtsystem.services.UserService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/loan-mgmt-system/auth")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), roles));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {

		if (userService.existsByEmail(registerRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email already exist!"));
		}

		// Create new user

		userService.createNewUser(registerRequest);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
