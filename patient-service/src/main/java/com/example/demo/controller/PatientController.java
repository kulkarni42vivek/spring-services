package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PatientRequestDto;
import com.example.demo.model.PatientResponseDto;
import com.example.demo.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/patient")
@Slf4j
@Tag(name="Patient",description = "api for managing patients")
public class PatientController {

	private final PatientService patientservice;
	
	PatientController(PatientService service){
		this.patientservice =service;
	}
	
	@Operation(summary = "Get Patients")
	@GetMapping("/getPatientData")
	public ResponseEntity<List<PatientResponseDto>>  getPatientInformation(){
		log.info("Get patientData ->> PatientController: getPatientData");
		return ResponseEntity.ok(this.patientservice.getPatients());
	}
	
	
	@PostMapping("/savePatient")
	public ResponseEntity<PatientResponseDto>  savePatient(@Valid @RequestBody PatientRequestDto request){
		PatientResponseDto response = this.patientservice.savePatient(request);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/updatePatient/{id}")
	public ResponseEntity<PatientResponseDto>  updatePatient(@PathVariable("id") UUID uuid, @Valid @RequestBody PatientRequestDto request){
		PatientResponseDto response = this.patientservice.updatePatient(uuid , request);
		return ResponseEntity.ok(response);
	}
}
