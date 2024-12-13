package com.example.maternityhome.service;

import com.example.maternityhome.model.Role;
import com.example.maternityhome.model.User;
import com.example.maternityhome.repository.RoleRepository;
import com.example.maternityhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public void registerUser(User user) {
        Role userRole = findRoleByName("USER");
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
    }
}
