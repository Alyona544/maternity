package com.example.maternityhome.service;

import com.example.maternityhome.model.Patient;
import com.example.maternityhome.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients(String sort, String sortDir) {
        Sort.Direction direction = sortDir != null && sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        if (sort != null && !sort.isEmpty()) {
            return patientRepository.findAll(Sort.by(direction, sort));
        } else {
            return patientRepository.findAll();
        }
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> searchPatients(String name, String gender, String doctorName) {
        if (name != null && !name.isEmpty()) {
            return patientRepository.findByPatientNameContaining(name);
        } else if (gender != null && !gender.isEmpty()) {
            return patientRepository.findByGender(gender);
        } else if (doctorName != null && !doctorName.isEmpty()) {
            return patientRepository.findByDoctor_DoctorName(doctorName);
        }
        return patientRepository.findAll();
    }
}
