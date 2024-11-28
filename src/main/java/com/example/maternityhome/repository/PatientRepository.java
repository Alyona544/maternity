package com.example.maternityhome.repository;

import com.example.maternityhome.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}