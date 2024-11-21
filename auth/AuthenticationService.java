package com.example.task_management_system.auth;

import com.example.task_management_system.config.JwtService;
import com.example.task_management_system.model.Role;
import com.example.task_management_system.model.User;
import com.example.task_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

        /**
     * Registers a new user in the system.
     * 
     * This method creates a new user account based on the provided registration details.
     * It checks if the email is already in use, creates a new User entity, saves it to the repository,
     * and generates a JWT token for the newly registered user.
     *
     * @param request The RegisterRequest object containing the user's registration details
     *                (firstname, lastname, email, and password).
     * @return AuthenticationResponse An object containing the JWT token for the newly registered user.
     * @throws RuntimeException If the email provided in the request is already in use.
     */
    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

        /**
     * Authenticates a user based on the provided credentials.
     * 
     * This method attempts to authenticate a user using their email and password.
     * If authentication is successful, it generates a JWT token for the authenticated user.
     *
     * @param request The AuthenticationRequest object containing the user's email and password.
     * @return AuthenticationResponse An object containing the JWT token for the authenticated user.
     * @throws org.springframework.security.core.AuthenticationException If authentication fails due to invalid credentials.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
