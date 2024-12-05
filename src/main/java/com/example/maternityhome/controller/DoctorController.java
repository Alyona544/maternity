package com.example.maternityhome.controller;

import com.example.maternityhome.model.Doctor;
import com.example.maternityhome.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctor_list";
    }

    @GetMapping("/{id}")
    public String getDoctorById(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id).orElse(null);
        model.addAttribute("doctor", doctor);
        return "doctor_detail";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }

    @GetMapping("/search")
    public String searchDoctors(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String specialization,
                                Model model) {
        List<Doctor> doctors = doctorService.searchDoctors(name, specialization);
        model.addAttribute("doctors", doctors);
        return "doctor_list";
    }
}
