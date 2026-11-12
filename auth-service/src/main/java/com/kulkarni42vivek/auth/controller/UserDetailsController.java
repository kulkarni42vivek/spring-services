package com.kulkarni42vivek.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kulkarni42vivek.auth.model.ApiResponse;
import com.kulkarni42vivek.auth.model.LoginModel;
import com.kulkarni42vivek.auth.service.LoginService;

@RestController
@RequestMapping("/getUserDetails")
public class UserDetailsController {
	
	private final LoginService loginService;

	UserDetailsController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/getUserInfo")
	public ApiResponse<LoginModel> registerUser(@RequestParam String email) {
		return loginService.getUserInformation(email);
	}
}
