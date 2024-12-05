package com.example.maternityhome.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    private String gender;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private String insurancePolicy;
    private LocalDateTime appointmentDateTime;

    @ManyToOne // Связь с врачом
    @JoinColumn(name = "doctor_id", referencedColumnName = "id") // Указываем ID врача для связи
    private Doctor doctor; // Связь с объектом Doctor

    // Геттеры и сеттеры (автоматически генерируются с помощью Lombok)
}
