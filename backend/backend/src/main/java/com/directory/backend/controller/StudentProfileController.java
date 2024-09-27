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

import com.directory.backend.entity.StudentProfile;
import com.directory.backend.service.StudentProfileService;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping
    public List<StudentProfile> getAllStudents() {
        return studentProfileService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudentById(@PathVariable Long id) {
        Optional<StudentProfile> studentProfile = studentProfileService.getStudentById(id);
        return studentProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentProfile> createStudent(@RequestBody StudentProfile studentProfile) {
        // Extract department ID from the nested department object in the request
        Long departmentId = studentProfile.getDepartment().getId();
        StudentProfile createdStudent = studentProfileService.createStudent(studentProfile, departmentId);
        if (createdStudent != null) {
            return ResponseEntity.ok(createdStudent);
        }
        return ResponseEntity.badRequest().build();  // Handle error case
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfile> updateStudent(@PathVariable Long id, @RequestBody StudentProfile studentProfile) {
        StudentProfile updatedStudent = studentProfileService.updateStudent(id, studentProfile);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentProfileService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
