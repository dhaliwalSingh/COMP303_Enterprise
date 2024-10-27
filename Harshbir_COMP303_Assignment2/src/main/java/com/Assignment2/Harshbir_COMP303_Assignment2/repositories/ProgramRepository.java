package com.Assignment2.Harshbir_COMP303_Assignment2.repositories;

import com.Assignment2.Harshbir_COMP303_Assignment2.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, String> {
    Program findByProgramCode(String programCode);
}
