package com.directory.backend.service;

import com.directory.backend.entity.Enrollment;
import com.directory.backend.entity.StudentProfile;
import com.directory.backend.entity.Course;
import com.directory.backend.repository.EnrollmentRepository;
import com.directory.backend.repository.StudentProfileRepository;
import com.directory.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // Enroll a student in a course
    public Enrollment enrollStudent(Long studentId, Long courseId, String grade) {
        Optional<StudentProfile> student = studentProfileRepository.findById(studentId);
        Optional<Course> course = courseRepository.findById(courseId);

        if (student.isPresent() && course.isPresent()) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student.get());
            enrollment.setCourse(course.get());
            enrollment.setGrade(grade);  // Optional field

            return enrollmentRepository.save(enrollment);
        }
        return null;  // Handle with proper exception
    }

    // Get enrollments by student
    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        Optional<StudentProfile> student = studentProfileRepository.findById(studentId);
        if (student.isPresent()) {
            return enrollmentRepository.findByStudent(student.get());
        }
        return null;  // Handle with proper exception
    }

    // Get enrollments by course
    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            return enrollmentRepository.findByCourse(course.get());
        }
        return null;  // Handle with proper exception
    }
}
