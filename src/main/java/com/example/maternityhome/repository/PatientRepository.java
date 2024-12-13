package com.example.maternityhome.repository;

import com.example.maternityhome.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByDoctor_DoctorName(String doctorName);
    List<Patient> findByPatientNameContaining(String name);
    List<Patient> findByGender(String gender);
}
