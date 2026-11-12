package com.example.demo.Entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id ;
	
	@NotNull
	private String name ;
	
	@NotNull
	@Email
	@Column(unique = true)
	private String email ;
	
	@NotNull
	private LocalDate  dob;
	
}
