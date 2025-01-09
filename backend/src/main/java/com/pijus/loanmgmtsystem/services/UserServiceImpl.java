package com.pijus.loanmgmtsystem.services;

import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pijus.loanmgmtsystem.model.ERole;
import com.pijus.loanmgmtsystem.model.Role;
import com.pijus.loanmgmtsystem.model.User;
import com.pijus.loanmgmtsystem.payload.request.RegisterRequest;
import com.pijus.loanmgmtsystem.repository.RoleRepository;
import com.pijus.loanmgmtsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	// register the user
	@Override
	public void createNewUser(RegisterRequest registerRequest) {

		User user = new User(registerRequest.getName(), registerRequest.getEmail(), registerRequest.getContact(),
				encoder.encode(registerRequest.getPassword()));

		Set<String> strRoles = registerRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
	}

	@Override
	public Boolean existsByEmail(String email) {

		Boolean isExist = userRepository.existsByEmail(email);
		return isExist;

	}
}
