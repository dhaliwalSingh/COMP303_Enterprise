package com.Assignment2.Harshbir_COMP303_Assignment2.controllers;

import com.Assignment2.Harshbir_COMP303_Assignment2.models.Enrollment;
import com.Assignment2.Harshbir_COMP303_Assignment2.models.Program;
import com.Assignment2.Harshbir_COMP303_Assignment2.models.Student;
import com.Assignment2.Harshbir_COMP303_Assignment2.repositories.EnrollmentRepository;
import com.Assignment2.Harshbir_COMP303_Assignment2.repositories.ProgramRepository;
import com.Assignment2.Harshbir_COMP303_Assignment2.repositories.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Step 1: Display checkout with programs to be enrolled
    @PostMapping("/checkout")
    public String checkout(@RequestParam List<String> programCode,
                           @RequestParam double amountPaid,
                           HttpSession session, Model model) {
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");
        if (loggedInStudent == null) {
            return "redirect:/login";
        }

        List<Program> programs = programRepository.findAllById(programCode);
        if (programs.isEmpty()) {
            model.addAttribute("error", "Programs not found.");
            return "error";
        }

        List<Enrollment> enrollments = new ArrayList<>();
        for (Program program : programs) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(loggedInStudent);
            enrollment.setProgram(program);
            enrollment.setAmountPaid(program.getFee()); // Set each program's specific fee
            enrollment.setStartDate(new Date());
            enrollments.add(enrollment);
        }




        model.addAttribute("enrollments", enrollments);
        return "payment"; // Adjust this if the page name is different
    }

    // Final enrollment save in confirmPayment
    @PostMapping("/confirmPayment")
    public String confirmPayment(
            @RequestParam Long studentId,
            @RequestParam List<String> programCode,
            @RequestParam List<Double> amountPaid,
            Model model
    ) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            model.addAttribute("error", "Student not found.");
            return "error";
        }

        List<Enrollment> enrollments = new ArrayList<>();
        for (int i = 0; i < programCode.size(); i++) {
            Program program = programRepository.findByProgramCode(programCode.get(i));
            if (program == null) {
                model.addAttribute("error", "Program not found for code: " + programCode.get(i));
                return "error";
            }

            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setProgram(program);
            enrollment.setAmountPaid(amountPaid.get(i));  // Assign the specific amount
            enrollment.setStartDate(new Date());
            enrollmentRepository.save(enrollment);

            enrollments.add(enrollment);
        }

        model.addAttribute("enrollments", enrollments);
        model.addAttribute("student", student);
        return "confirmation";
    }



    @GetMapping("/index")
    public String showHomePage(Model model, HttpSession session) {
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");
        if (loggedInStudent == null) {
            return "redirect:/login"; // Redirect to login if the student is not logged in
        }

        List<Enrollment> enrolledCourses = enrollmentRepository.findByStudent(loggedInStudent);
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>(); // Initialize with an empty list if null
        }
        model.addAttribute("enrolledCourses", enrolledCourses);
        model.addAttribute("loggedInStudent", loggedInStudent);
        return "index";
    }
}
