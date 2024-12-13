package com.example.maternityhome.controller;

import com.example.maternityhome.model.Doctor;
import com.example.maternityhome.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping("/doctors")
    public String listDoctors(Model model,
                              @RequestParam(name = "sort", required = false) String sort,
                              @RequestParam(name = "sortDir", required = false) String sortDir) {
        List<Doctor> doctors = doctorService.getAllDoctors(sort, sortDir);
        model.addAttribute("doctors", doctors);
        model.addAttribute("doctor", new Doctor());
        return "doctor_list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/doctors/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping("/doctors/search")
    public String searchDoctors(@RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "specialization", required = false) String specialization,
                                Model model) {
        List<Doctor> doctors = doctorService.searchDoctors(name, specialization);
        model.addAttribute("doctors", doctors);
        model.addAttribute("doctor", new Doctor());
        return "doctor_list";
    }
}
