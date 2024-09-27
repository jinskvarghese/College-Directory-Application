package com.directory.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.directory.backend.entity.Course;
import com.directory.backend.entity.Department;
import com.directory.backend.entity.FacultyProfile;
import com.directory.backend.repository.CourseRepository;
import com.directory.backend.repository.DepartmentRepository;
import com.directory.backend.repository.FacultyProfileRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course, Long facultyId, Long departmentId) {
        Optional<FacultyProfile> faculty = facultyProfileRepository.findById(facultyId);
        Optional<Department> department = departmentRepository.findById(departmentId);

        if (faculty.isPresent() && department.isPresent()) {
            course.setFaculty(faculty.get());
            course.setDepartment(department.get());
            return courseRepository.save(course);
        }

        return null;  // Handle properly with exceptions or validation
    }

    public Course updateCourse(Long id, Course updatedCourse, Long facultyId, Long departmentId) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        Optional<FacultyProfile> faculty = facultyProfileRepository.findById(facultyId);
        Optional<Department> department = departmentRepository.findById(departmentId);

        if (existingCourse.isPresent() && faculty.isPresent() && department.isPresent()) {
            Course course = existingCourse.get();
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            course.setFaculty(faculty.get());
            course.setDepartment(department.get());
            return courseRepository.save(course);
        }
        return null;  // Handle better with exception handling
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
