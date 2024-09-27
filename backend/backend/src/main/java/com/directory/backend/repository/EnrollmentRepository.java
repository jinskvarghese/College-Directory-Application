package com.directory.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.directory.backend.entity.Course;
import com.directory.backend.entity.Enrollment;
import com.directory.backend.entity.StudentProfile;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Find enrollments by student
    List<Enrollment> findByStudent(StudentProfile student);

    // Find enrollments by course
    List<Enrollment> findByCourse(Course course)
    ;
}