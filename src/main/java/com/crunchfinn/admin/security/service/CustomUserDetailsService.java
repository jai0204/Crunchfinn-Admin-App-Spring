package com.crunchfinn.admin.security.service;

import com.crunchfinn.admin.security.entity.User;
import com.crunchfinn.admin.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        String normalizedUsername = username.trim();
        User user = userRepository.findByUsername(normalizedUsername)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name()) // IMPORTANT
                .disabled(!Boolean.TRUE.equals(user.getActive())) //  uses is_active
                .build();
    }
}