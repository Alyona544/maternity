package com.example.maternityhome.controller;

import com.example.maternityhome.forms.LoginForm;
import com.example.maternityhome.model.User;
import com.example.maternityhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login";
        }

        // Логика аутентификации пользователя
        User user = userService.findByUsername(loginForm.getUsername());
        if (user != null && passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
            // Успешная аутентификация
            // Установите сессию или другие необходимые действия
            return "redirect:/patient_list";
        } else {
            // Неуспешная аутентификация
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }
}
