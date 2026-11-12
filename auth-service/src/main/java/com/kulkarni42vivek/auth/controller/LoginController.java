package com.kulkarni42vivek.auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kulkarni42vivek.auth.model.ApiResponse;
import com.kulkarni42vivek.auth.model.LoginModel;
import com.kulkarni42vivek.auth.security.JwtService;
import com.kulkarni42vivek.auth.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	private final LoginService loginservice;
	private final AuthenticationManager authenticationmanager;
	private final JwtService jwtservice;

	public LoginController(LoginService loginservice, AuthenticationManager authenticationmanager,
			JwtService jwtservice) {
		this.loginservice = loginservice;
		this.authenticationmanager = authenticationmanager;
		this.jwtservice = jwtservice;
	}

	@PostMapping("/register")
	public ApiResponse<LoginModel> registerUser(@RequestBody LoginModel loginmodel) {
		return loginservice.registerUser(loginmodel);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginModel request) {

		Authentication authentication = authenticationmanager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		UserDetails user = (UserDetails) authentication.getPrincipal();

		String token = this.jwtservice.generateToken(user);

		return ResponseEntity.ok(Map.of("accessToken", token, "tokenType", "Bearer", "expiresIn", 90000));
	}

}
