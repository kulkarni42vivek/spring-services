package com.example.demo.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Patient;
import com.example.demo.Exception.EmailAlreadyExistsException;
import com.example.demo.Exception.PatientNotFound;
import com.example.demo.model.PatientRequestDto;
import com.example.demo.model.PatientResponseDto;
import com.example.demo.repo.PatientRepo;

@Service
public class PatientService {
	
	private final PatientRepo repo ;
	PatientService(PatientRepo repo){
		this.repo = repo;
	}
	
	public List<PatientResponseDto> getPatients(){
		List<Patient> list =repo.findAll();
		
		List<PatientResponseDto> response =  list.stream().map(obj-> PatientResponseDto.builder()
				.name(obj.getName())
				.email(obj.getEmail())
				.id(obj.getId())
				.dob(obj.getDob())
				.build()).collect(Collectors.toList());
		
		return response;
	}

	public PatientResponseDto savePatient(PatientRequestDto request) {
		
		if(this.repo.existsByEmail(request.getEmail())) {
			throw new EmailAlreadyExistsException("Email already exist");
		}
		
		Patient patient = Patient.builder()
				.name(request.getName())
				.dob(request.getDob())
				.email(request.getEmail())
				.build();
		Patient patientsave = repo.save(patient);
		return PatientResponseDto.builder()
				.dob(patient.getDob())
				.name(patient.getName())
				.email(patient.getEmail())
				.build();
		
	}
	
	public PatientResponseDto updatePatient(UUID uuid ,	PatientRequestDto request) {
		
		Patient patient = this.repo.findById(uuid).orElseThrow(()->{
			throw new PatientNotFound(uuid+ " not found");
		});
		
		patient.setName(request.getName());
		patient.setDob(request.getDob());
		patient.setEmail(request.getEmail());
		
		Patient response = this.repo.save(patient);
		
		return PatientResponseDto.builder()
				.dob(response.getDob())
				.name(response.getName())
				.email(response.getEmail())
				.build();
		
	}
}
