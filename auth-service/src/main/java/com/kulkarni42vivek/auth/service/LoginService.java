package com.kulkarni42vivek.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kulkarni42vivek.auth.exception.UserInformationNotFoundException;
import com.kulkarni42vivek.auth.model.ApiResponse;
import com.kulkarni42vivek.auth.model.LoginModel;
import com.kulkarni42vivek.auth.repo.LoginDao;

@Service
public class LoginService {
	
	private final LoginDao logindao;
	
	public LoginService(LoginDao logindao){
		this.logindao= logindao;
	}

	@Transactional
	public ApiResponse<LoginModel> registerUser(LoginModel loginmodel) {
		 boolean isEmailAlreadyPresent = this.logindao.isEmailPresent(loginmodel.getEmail());
		 if(isEmailAlreadyPresent) {
			 loginmodel.setPassword(null);
			 return ApiResponse.<LoginModel>builder()
					 .data(loginmodel)
					 .errorCode("Test")
					 .errorMessage("User Already Exists")
					 .build();
		 }
		 else {
			 this.logindao.saverUserDetails(loginmodel);
			 loginmodel.setPassword(null);
			 return ApiResponse.<LoginModel>builder()
					 .data(loginmodel)
					 .errorCode("Test")
					 .errorMessage("User added successfully")
					 .build();
		 }
	}

	public ApiResponse<LoginModel> getUserInformation(String email) {
		 Optional<LoginModel> loginModel = this.logindao.getUserInformation(email);
		 LoginModel response = loginModel.orElseThrow(() -> new UserInformationNotFoundException(
                 "User not found with username: " + email
         ));
		 
	    return ApiResponse.<LoginModel>builder()
	            .data(response)
	            .errorCode(null)
	            .errorMessage("User information fetched successfully")
	            .build();
	}
}
