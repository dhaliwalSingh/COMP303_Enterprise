package com.HatshbirSingh_COMP303_Assignment3.controller;

import com.HatshbirSingh_COMP303_Assignment3.model.Seeker;
import com.HatshbirSingh_COMP303_Assignment3.services.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seeker")
public class SeekerController {

    @Autowired
    private SeekerService seekerService;

    // Get all seekers
    @GetMapping
    public List<Seeker> getAllSeekers() {
        return seekerService.getAllSeekers();
    }

    // Get a seeker by ID
    @GetMapping("/{id}")
    public Seeker getSeekerById(@PathVariable Integer id) {
        return seekerService.getSeekerById(id);
    }

    // Add a new seeker
    @PostMapping
    public Seeker addSeeker(@RequestBody Seeker seeker) {
        return seekerService.addSeeker(seeker);
    }

    // Update a seeker by ID
    @PutMapping("/{id}")
    public Seeker updateSeeker(@PathVariable Integer id, @RequestBody Seeker seeker) {
        return seekerService.updateSeeker(id, seeker);
    }

    // Delete a seeker by ID
    @DeleteMapping("/{id}")
    public void deleteSeeker(@PathVariable Integer id) {
        seekerService.deleteSeeker(id);
    }
}
