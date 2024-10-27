package com.Assignment2.Harshbir_COMP303_Assignment2.repositories;

import com.Assignment2.Harshbir_COMP303_Assignment2.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom method to find a student by userName and password
    Optional<Student> findByUserNameAndPassword(String userName, String password);
    Optional<Student> findByUserName(String userName);


}
