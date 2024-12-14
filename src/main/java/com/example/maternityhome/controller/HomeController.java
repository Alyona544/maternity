package com.example.maternityhome.controller;

import com.example.maternityhome.model.User;
import com.example.maternityhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            User user = userService.findByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
        }
        return "home"; // Ensure this template exists
    }

    @GetMapping("/home/doctors")
    public String doctors() {
        return "doctor_list"; // Ensure this template exists
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/registerAdmin")
    public String registerAdmin() {
        return "registerAdmin";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }
}
