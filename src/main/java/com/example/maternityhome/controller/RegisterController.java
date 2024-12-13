package com.example.maternityhome.controller;

import com.example.maternityhome.forms.RegisterForm;
import com.example.maternityhome.model.User;
import com.example.maternityhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerForm") RegisterForm registerForm, Model model) {
        // Check if username already exists
        if (userService.findByUsername(registerForm.getUsername()) != null) {
            model.addAttribute("error", "Username already exists!");
            return "register"; // Return to registration page with error message
        }

        User user = new User();
        user.setUsername(registerForm.getUsername());
        user.setEmail(registerForm.getEmail());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));

        // Save user with "USER" role
        userService.registerUser(user);

        return "redirect:/login"; // Redirect to login after successful registration
    }

}
