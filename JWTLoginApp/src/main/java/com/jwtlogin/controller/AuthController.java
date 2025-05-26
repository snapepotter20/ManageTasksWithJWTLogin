package com.jwtlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtlogin.dto.AuthRequest;
import com.jwtlogin.dto.AuthResponse;
import com.jwtlogin.entity.User;
import com.jwtlogin.repository.UserRepository;
import com.jwtlogin.service.UserService;
import com.jwtlogin.util.JWTUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody AuthRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        userRepository.save(user);
        return "User registered!";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity
                .status(403)
                .body("Authentication failed: " + e.getMessage());
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(token);
    }
}
