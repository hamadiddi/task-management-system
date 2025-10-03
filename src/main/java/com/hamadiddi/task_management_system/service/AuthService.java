package com.hamadiddi.task_management_system.service;

import com.hamadiddi.task_management_system.entity.User;
import com.hamadiddi.task_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow();
        if (passwordEncoder.matches(password, user.getPasswordHash())) {
            return tokenProvider.generateToken(user.getUsername());
        }
        throw new RuntimeException("Invalid credentials");
    }
}