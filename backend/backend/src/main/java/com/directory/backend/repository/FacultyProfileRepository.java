package com.directory.backend.repository;

import com.directory.backend.entity.FacultyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
}