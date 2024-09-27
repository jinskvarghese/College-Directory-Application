package com.directory.backend.controller;

import com.directory.backend.service.JwtUtil;
import com.directory.backend.service.UserDetailsServiceImpl;
import com.directory.backend.entity.AuthRequest;
import com.directory.backend.entity.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final String token = jwtUtil.generateToken(authRequest.getUsername());
        return new AuthResponse(token);
    }
}
