package com.example.maternityhome.service;

import com.example.maternityhome.model.Patient;
import com.example.maternityhome.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;


    // Retrieve all patients.
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    // Save a new or updated patient.
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    // Delete a patient by ID.
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    // Find a specific patient by ID.
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
}
