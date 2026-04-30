package com.crunchfinn.admin.security.config;

import com.crunchfinn.admin.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        // allow static files
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // allow login page
                        .requestMatchers("/").permitAll()

                        // allow banks list page only
                        .requestMatchers("/banks").authenticated()

                        // admin only
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/banks/**").hasRole("ADMIN")
                        .requestMatchers("/partners/*/edit", "/partners/*/delete").hasRole("ADMIN")

                        // everything else requires login
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/")                 // your custom login page (GET)
                        .loginProcessingUrl("/login")        // Spring handles POST
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/?logout=true")
                        .permitAll()
                )

                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied"))

                .userDetailsService(userDetailsService);

        return http.build();
    }

    // Password encoder (VERY IMPORTANT)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}