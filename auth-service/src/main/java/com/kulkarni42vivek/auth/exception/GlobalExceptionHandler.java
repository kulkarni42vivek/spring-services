package com.kulkarni42vivek.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kulkarni42vivek.auth.model.ApiResponse;
import com.kulkarni42vivek.auth.model.ErrorModel;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(CustomSQLException.class)
	    public ResponseEntity<ApiResponse<ErrorModel>> handleSQLeception(
	    		CustomSQLException ex,
	            HttpServletRequest request) {

		 ApiResponse<ErrorModel> error = ApiResponse.<ErrorModel>builder()
				 .data(ErrorModel.builder().errorCode("SQL").errorMessage("SQL failed").build())
				 .errorCode("SQL")
				 .errorMessage("SQL")
				 .build();
				 

	        return ResponseEntity.status(HttpStatus.OK)
	                .body(error);
	    }
	 
	 
	 
	 @ExceptionHandler(UserInformationNotFoundException.class)
	    public ResponseEntity<ApiResponse<ErrorModel>> handleUserInfomationNotFoundException(
	    		UserInformationNotFoundException ex,
	            HttpServletRequest request) {

		 ApiResponse<ErrorModel> error = ApiResponse.<ErrorModel>builder()
				 .data(ErrorModel.builder().errorCode("USER_NOT_FOUND").errorMessage("User information not found").build())
				 .errorCode("USER_NOT_FOUND")
				 .errorMessage("User information not found")
				 .build();
				 

	        return ResponseEntity.status(HttpStatus.OK)
	                .body(error);
	    }
}
