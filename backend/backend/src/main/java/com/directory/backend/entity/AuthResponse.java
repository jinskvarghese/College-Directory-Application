package com.directory.backend.entity;

public class AuthResponse {

    private String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    // Getter for JWT
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
