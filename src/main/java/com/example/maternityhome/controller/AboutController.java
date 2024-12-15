//package com.example.maternityhome.controller;
//
//import com.example.maternityhome.model.User;
//import com.example.maternityhome.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class AboutController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/about")
//    public String about(Model model) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            User user = userService.findByUsername(userDetails.getUsername());
//            model.addAttribute("user", user);
//        }
//
//        return "about";
//    }
//}
