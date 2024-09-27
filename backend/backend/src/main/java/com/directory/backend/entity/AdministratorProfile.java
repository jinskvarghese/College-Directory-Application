package com.directory.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "administrator_profile")
public class AdministratorProfile {

    @Id
    private Long userId;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Getters and Setters
}
