package com.Assignment2.Harshbir_COMP303_Assignment2.repositories;

import com.Assignment2.Harshbir_COMP303_Assignment2.models.Enrollment;
import com.Assignment2.Harshbir_COMP303_Assignment2.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(Student student);
}
