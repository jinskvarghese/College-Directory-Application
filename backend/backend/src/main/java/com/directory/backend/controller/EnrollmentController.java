package com.directory.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.directory.backend.entity.Enrollment;
import com.directory.backend.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Get all enrollments
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // Enroll a student in a course
    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(
            @RequestParam Long studentId, @RequestParam Long courseId, @RequestParam(required = false) String grade) {
        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId, grade);
        if (enrollment != null) {
            return ResponseEntity.ok(enrollment);
        }
        return ResponseEntity.badRequest().build();
    }

    // Get enrollments by student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
        if (enrollments != null) {
            return ResponseEntity.ok(enrollments);
        }
        return ResponseEntity.notFound().build();
    }

    // Get enrollments by course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
        if (enrollments != null) {
            return ResponseEntity.ok(enrollments);
        }
        return ResponseEntity.notFound().build();
    }
}
