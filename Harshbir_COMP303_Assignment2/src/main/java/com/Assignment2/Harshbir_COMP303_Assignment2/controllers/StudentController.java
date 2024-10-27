package com.Assignment2.Harshbir_COMP303_Assignment2.controllers;

import com.Assignment2.Harshbir_COMP303_Assignment2.models.Enrollment;
import com.Assignment2.Harshbir_COMP303_Assignment2.models.Student;
import com.Assignment2.Harshbir_COMP303_Assignment2.repositories.EnrollmentRepository;
import com.Assignment2.Harshbir_COMP303_Assignment2.repositories.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Optional<Student> optionalStudent = studentRepository.findByUserNameAndPassword(username, password);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            session.setAttribute("loggedInStudent", student); // Set session attribute
            return "redirect:/"; // Redirect to home page
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    // Show the registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student, Model model) {
        studentRepository.save(student);
        model.addAttribute("successMessage", "Registration successful!");
        return "login";
    }

    @GetMapping("/")
    public String homepage(HttpSession session, Model model) {
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");

        if (loggedInStudent == null) {
            return "redirect:/login";
        }

        List<Enrollment> enrolledCourses = enrollmentRepository.findByStudent(loggedInStudent);
        model.addAttribute("loggedInStudent", loggedInStudent);
        model.addAttribute("enrolledCourses", enrolledCourses);

        return "index";
    }

    @GetMapping("/editProfile")
    public String editProfile(HttpSession session, Model model) {
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");

        if (loggedInStudent == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }

        model.addAttribute("student", loggedInStudent);
        return "editProfile"; // Return the edit profile view
    }

    @PostMapping("/editProfile")
    public String updateProfile(@ModelAttribute Student student, HttpSession session) {
        Student existingStudent = (Student) session.getAttribute("loggedInStudent");

        if (existingStudent != null) {
            // Retain the existing password if the password field is empty
            if (student.getPassword() == null || student.getPassword().isEmpty()) {
                student.setPassword(existingStudent.getPassword());
            }

            // Update the student details with the existing studentId
            student.setStudentId(existingStudent.getStudentId()); // Ensure we update the existing record
            studentRepository.save(student);

            // Update the session
            session.setAttribute("loggedInStudent", student);
        }

        return "redirect:/"; // Redirect to home after update
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
