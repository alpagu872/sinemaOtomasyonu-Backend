package org.alpagu.sinemaotomasyonu.Api.Controllers;

import org.alpagu.sinemaotomasyonu.Business.Concretes.UserDetailsServiceImpl;
import org.alpagu.sinemaotomasyonu.Business.Concretes.UserServiceImpl;
import org.alpagu.sinemaotomasyonu.Core.utilities.Jwt.JwtUtil;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.UserRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.User;
import org.alpagu.sinemaotomasyonu.Entities.Dtos.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping("/api")

public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User webUser) {
        if (userRepository.existsByPhoneNumber(webUser.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this phone number already exists.");
        }

        // Encode the password before saving the user

        webUser.setWebUserId(userServiceImpl.generateNextUserId());
        webUser.setPassword(passwordEncoder.encode(webUser.getPassword()));

        userRepository.save(webUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> createAuthenticationToken(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user based on the phone number and password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword())
            );
        } catch (Exception e) {
            // If authentication fails, return a 401 Unauthorized status with an error message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Incorrect phone number or password"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getPhoneNumber());
        final User user = userRepository.findByPhoneNumber(loginRequest.getPhoneNumber());

        // Kullanıcı kimliği ve rol bilgilerini JWT'ye ekle
        final String jwt = jwtUtil.generateToken(userDetails, user.getWebUserId(), user.getRole().toString());

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);

        return ResponseEntity.ok(response);
    }

}
