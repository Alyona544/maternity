package com.example.maternityhome.service;

import com.example.maternityhome.model.Role;
import com.example.maternityhome.model.User;
import com.example.maternityhome.repository.RoleRepository;
import com.example.maternityhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        // Шифрование пароля
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Установка ролей
        Role userRole = roleRepository.findById(2L).orElseThrow(() -> new IllegalArgumentException("Role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        // Сохранение пользователя в базу данных
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
