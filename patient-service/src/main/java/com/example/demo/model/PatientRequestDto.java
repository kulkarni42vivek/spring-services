package com.example.demo.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientRequestDto {

	@NotBlank
	@Size(max = 100,message = "large size")
	private String name ;
	
	@NotBlank
	@Email
	private String email ;
	
	private LocalDate  dob;
}
