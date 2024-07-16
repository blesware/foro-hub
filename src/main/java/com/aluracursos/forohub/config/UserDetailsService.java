package com.aluracursos.forohub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("user").password(passwordEncoder.encode("password")).roles("USER").build());

        manager.createUser(User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").build());

        return manager;
    }
}
