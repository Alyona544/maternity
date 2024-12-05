package com.example.maternityhome.repository;

import com.example.maternityhome.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByDoctorNameContaining(String name);
    List<Doctor> findBySpecialization(String specialization);
}
