package com.example.maternityhome.service;

import com.example.maternityhome.model.Doctor;
import com.example.maternityhome.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> searchDoctors(String name, String specialization) {
        if (name != null && !name.isEmpty()) {
            return doctorRepository.findByDoctorNameContaining(name);
        } else if (specialization != null && !specialization.isEmpty()) {
            return doctorRepository.findBySpecialization(specialization);
        }
        return doctorRepository.findAll();
    }
}