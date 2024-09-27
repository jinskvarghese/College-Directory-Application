package com.directory.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.directory.backend.entity.Department;
import com.directory.backend.entity.FacultyProfile;
import com.directory.backend.repository.DepartmentRepository;
import com.directory.backend.repository.FacultyProfileRepository;

@Service
public class FacultyProfileService {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<FacultyProfile> getAllFaculty() {
        return facultyProfileRepository.findAll();
    }

    public Optional<FacultyProfile> getFacultyById(Long id) {
        return facultyProfileRepository.findById(id);
    }

    public FacultyProfile createFaculty(FacultyProfile facultyProfile, Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            facultyProfile.setDepartment(department.get());
            return facultyProfileRepository.save(facultyProfile);
        }
        return null;  // Handle with proper exception handling
    }

    public FacultyProfile updateFaculty(Long id, FacultyProfile updatedFaculty, Long departmentId) {
        Optional<FacultyProfile> existingFaculty = facultyProfileRepository.findById(id);
        Optional<Department> department = departmentRepository.findById(departmentId);

        if (existingFaculty.isPresent() && department.isPresent()) {
            FacultyProfile faculty = existingFaculty.get();
            faculty.setPhoto(updatedFaculty.getPhoto());
            faculty.setDepartment(department.get());
            faculty.setOfficeHours(updatedFaculty.getOfficeHours());
            return facultyProfileRepository.save(faculty);
        }
        return null;  // Handle with proper exception handling
    }

    public void deleteFaculty(Long id) {
        facultyProfileRepository.deleteById(id);
    }
}
