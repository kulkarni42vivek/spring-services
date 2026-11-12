package com.example.demo.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, UUID> {
	boolean existsByEmail(String email);
}
