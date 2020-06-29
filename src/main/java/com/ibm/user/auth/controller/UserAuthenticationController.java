package com.ibm.user.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.user.auth.domain.AuthUserRequest;
import com.ibm.user.auth.util.JwtUtil;

@RestController
public class UserAuthenticationController {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String getHome() {

		return "<h1>Welcome </h1>";
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthUserRequest authUserRequest) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUserRequest.getUsername(),
					authUserRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("Invalide Username/Password");
		}
		System.out.println("token:" + jwtUtil.generateToken(authUserRequest.getUsername()));

		return jwtUtil.generateToken(authUserRequest.getUsername());
	}

}
