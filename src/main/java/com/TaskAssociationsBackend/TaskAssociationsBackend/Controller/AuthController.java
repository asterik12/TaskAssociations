package com.TaskAssociationsBackend.TaskAssociationsBackend.Controller;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.ERole;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Role;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.User;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.RoleRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.UserRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Security.jwt.JwtUtils;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Security.services.UserDetailsImpl;
import com.TaskAssociationsBackend.TaskAssociationsBackend.payload.JwtResponse;
import com.TaskAssociationsBackend.TaskAssociationsBackend.payload.LoginRequest;
import com.TaskAssociationsBackend.TaskAssociationsBackend.payload.MessageResponse;
import com.TaskAssociationsBackend.TaskAssociationsBackend.payload.SignupRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
	    List<String> roles = userDetails.getAuthorities().stream()
	        .map(item -> item.getAuthority())
	        .collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles
				));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
		if(userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Username is already exists"));
		}
		if(userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Email is already exists"));
		}
		
		//Create new user
		User user = new User(signupRequest.getUsername(),
				signupRequest.getEmail(),
				encoder.encode(signupRequest.getPassword()));
		
		Set<String> strRoles = signupRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
		}
		else {
			strRoles.forEach(role -> {
				switch(role) {
				case "admin": 
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(adminRole);
					break;
				case "moderator": 
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(modRole);
					break;
					
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
						roles.add(userRole);
					
				}

			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully."));
	}
}
