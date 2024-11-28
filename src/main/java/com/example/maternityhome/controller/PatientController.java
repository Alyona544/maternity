package com.example.maternityhome.controller;

import com.example.maternityhome.model.Patient;
import com.example.maternityhome.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patient_list")
    public String patientList(Model model) {
        List<Patient> patients = patientService.findAll();
        model.addAttribute("patientList", patients);
        model.addAttribute("roles", getRoles()); // Убедитесь, что этот метод возвращает список ролей
        return "patient_list"; // Убедитесь, что этот шаблон существует
    }

    @PostMapping("/patient/add")
    public String addPatient(@RequestBody Patient patient) {
        patientService.save(patient);
        return "redirect:/patient_list";
    }

    @GetMapping("/patient/edit/{id}")
    @ResponseBody
    public Patient editPatient(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @PostMapping("/patient/edit/{id}")
    public String updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        patient.setId(id);
        patientService.save(patient);
        return "redirect:/patient_list";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return "redirect:/patient_list";
    }

    private List<String> getRoles() {
        // Реализуйте этот метод для получения списка ролей текущего пользователя
        return List.of("ADMIN"); // Пример
    }
}
