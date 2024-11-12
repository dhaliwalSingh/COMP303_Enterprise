package com.HatshbirSingh_COMP303_Assignment3.controller;

import com.HatshbirSingh_COMP303_Assignment3.model.BloodBank;
import com.HatshbirSingh_COMP303_Assignment3.services.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloodbank")
public class BloodBankController {

    @Autowired
    private BloodBankService bloodBankService;

    // Get all blood banks
    @GetMapping
    public List<BloodBank> getAllBloodBanks() {
        return bloodBankService.getAllBloodBanks();
    }

    // Get a blood bank by ID
    @GetMapping("/{id}")
    public BloodBank getBloodBankById(@PathVariable Integer id) {
        return bloodBankService.getBloodBankById(id);
    }

    // Add a new blood bank
    @PostMapping
    public BloodBank addBloodBank(@RequestBody BloodBank bloodBank) {
        return bloodBankService.addBloodBank(bloodBank);
    }

    // Update a blood bank by ID
    @PutMapping("/{id}")
    public BloodBank updateBloodBank(@PathVariable Integer id, @RequestBody BloodBank bloodBank) {
        return bloodBankService.updateBloodBank(id, bloodBank);
    }

    // Delete a blood bank by ID
    @DeleteMapping("/{id}")
    public void deleteBloodBank(@PathVariable Integer id) {
        bloodBankService.deleteBloodBank(id);
    }
}
