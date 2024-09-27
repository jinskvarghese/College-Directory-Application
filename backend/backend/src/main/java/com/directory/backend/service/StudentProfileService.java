package com.directory.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.directory.backend.entity.Department;
import com.directory.backend.entity.StudentProfile;
import com.directory.backend.repository.DepartmentRepository;
import com.directory.backend.repository.StudentProfileRepository;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    public Optional<StudentProfile> getStudentById(Long id) {
        return studentProfileRepository.findById(id);
    }

    public StudentProfile createStudent(StudentProfile studentProfile, Long departmentId) {
        // Fetch the Department entity based on the departmentId
        Optional<Department> department = departmentRepository.findById(departmentId);

        if (department.isPresent()) {
            studentProfile.setDepartment(department.get());
            return studentProfileRepository.save(studentProfile);
        }

        return null;  // Handle this with proper exception handling
    }

    public StudentProfile updateStudent(Long id, StudentProfile updatedStudentProfile) {
        Optional<StudentProfile> existingStudent = studentProfileRepository.findById(id);
        if (existingStudent.isPresent()) {
            StudentProfile student = existingStudent.get();
            student.setPhoto(updatedStudentProfile.getPhoto());
            student.setDepartment(updatedStudentProfile.getDepartment());
            student.setYear(updatedStudentProfile.getYear());
            return studentProfileRepository.save(student);
        }
        return null;  // Handle better with exception handling
    }

    public void deleteStudent(Long id) {
        studentProfileRepository.deleteById(id);
    }
}
