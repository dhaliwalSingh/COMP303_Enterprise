package com.Assignment2.Harshbir_COMP303_Assignment2.controllers;

import com.Assignment2.Harshbir_COMP303_Assignment2.models.Program;
import com.Assignment2.Harshbir_COMP303_Assignment2.models.Student;
import com.Assignment2.Harshbir_COMP303_Assignment2.repositories.ProgramRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
public class ProgramController {
    @Autowired
    private ProgramRepository programRepository;

    @GetMapping("/api/programs")
    @ResponseBody
    public List<Program> getPrograms() {
        return programRepository.findAll(); // Return all programs in JSON format
    }

    @GetMapping("/programs")
    public String viewPrograms(Model model, HttpSession session) {
        // Check if a student is logged in
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");
        if (loggedInStudent == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }

        model.addAttribute("programs", programRepository.findAll());
        model.addAttribute("loggedInStudent", loggedInStudent); // Add the loggedInStudent to the model
        return "program";
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/programs").allowedOrigins("http://localhost:8080"); // Adjust this to your frontend URL
        }
    }
}
