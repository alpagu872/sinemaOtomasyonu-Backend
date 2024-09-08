package org.alpagu.sinemaotomasyonu.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("test") // Ensure this configuration is only active during tests
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/resources/**").permitAll() // Permit all requests to /resources/**
                                .anyRequest().authenticated() // Require authentication for any other request
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF for testing purposes if needed

        return http.build();
}


}
