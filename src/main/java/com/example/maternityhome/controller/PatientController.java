package com.example.maternityhome.controller;

import com.example.maternityhome.model.Patient;
import com.example.maternityhome.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String getAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patient_list";
    }

    @GetMapping("/{id}")
    public String getPatientById(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id).orElse(null);
        model.addAttribute("patient", patient);
        return "patient_detail";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    @GetMapping("/search")
    public String searchPatients(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String gender,
                                 @RequestParam(required = false) String doctorName,
                                 Model model) {
        List<Patient> patients = patientService.searchPatients(name, gender, doctorName);
        model.addAttribute("patients", patients);
        return "patient_list";
    }
}
