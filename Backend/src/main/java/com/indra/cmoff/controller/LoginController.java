package com.indra.cmoff.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indra.cmoff.service.impl.UserDetailsImpl;
import com.indra.cmoff.auth.jwt.JwtUtils;
import com.indra.cmoff.auth.payload.JwtResponse;
import com.indra.cmoff.auth.payload.LoginRequest;



@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.OPTIONS })
@RestController
public class LoginController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		if (authentication.getPrincipal() instanceof UserDetailsImpl) {
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			return ResponseEntity.ok(JwtResponse.buildUserDetails(jwt, userDetails));
		} else {
			User userDetails = (User) authentication.getPrincipal();
			return ResponseEntity.ok(JwtResponse.buildSuperUser(jwt, userDetails));
		}

	}

}
