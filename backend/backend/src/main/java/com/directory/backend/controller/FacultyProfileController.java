package com.directory.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.directory.backend.entity.FacultyProfile;
import com.directory.backend.service.FacultyProfileService;

@RestController
@RequestMapping("/api/faculty")
public class FacultyProfileController {

    @Autowired
    private FacultyProfileService facultyProfileService;

    @GetMapping
    public List<FacultyProfile> getAllFaculty() {
        return facultyProfileService.getAllFaculty();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyProfile> getFacultyById(@PathVariable Long id) {
        Optional<FacultyProfile> facultyProfile = facultyProfileService.getFacultyById(id);
        return facultyProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FacultyProfile> createFaculty(@RequestBody FacultyProfile facultyProfile) {
        Long departmentId = facultyProfile.getDepartment().getId();
        FacultyProfile createdFaculty = facultyProfileService.createFaculty(facultyProfile, departmentId);
        if (createdFaculty != null) {
            return ResponseEntity.ok(createdFaculty);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyProfile> updateFaculty(@PathVariable Long id, @RequestBody FacultyProfile facultyProfile) {
        Long departmentId = facultyProfile.getDepartment().getId();
        FacultyProfile updatedFaculty = facultyProfileService.updateFaculty(id, facultyProfile, departmentId);
        if (updatedFaculty != null) {
            return ResponseEntity.ok(updatedFaculty);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyProfileService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
