package com.example.maternityhome.controller;

import com.example.maternityhome.model.Doctor;
import com.example.maternityhome.model.Patient;
import com.example.maternityhome.model.User;
import com.example.maternityhome.service.DoctorService;
import com.example.maternityhome.service.PatientService;
import com.example.maternityhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping
    public String getAllPatients(@RequestParam(required = false) String sort,
                                 @RequestParam(required = false) String sortDir,
                                 Model model) {
        List<Patient> patients = patientService.getAllPatients(sort, sortDir);
        List<Doctor> doctors = doctorService.getAllDoctors(null, null);
        model.addAttribute("patients", patients);
        model.addAttribute("doctors", doctors);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
        }

        return "patient_list";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping("/search")
    public String searchPatients(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String gender,
                                 @RequestParam(required = false) String doctorName,
                                 Model model) {
        List<Patient> patients = patientService.searchPatients(name, gender, doctorName);
        List<Doctor> doctors = doctorService.getAllDoctors(null, null);
        model.addAttribute("patients", patients);
        model.addAttribute("doctors", doctors);

        // Add user information to the model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
        }

        return "patient_list";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }
}
