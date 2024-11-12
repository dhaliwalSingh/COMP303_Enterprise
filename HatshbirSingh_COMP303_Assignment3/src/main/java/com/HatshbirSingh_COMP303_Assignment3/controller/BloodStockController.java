package com.HatshbirSingh_COMP303_Assignment3.controller;

import com.HatshbirSingh_COMP303_Assignment3.model.BloodStock;
import com.HatshbirSingh_COMP303_Assignment3.services.BloodStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloodstock")
public class BloodStockController {

    @Autowired
    private BloodStockService bloodStockService;

    // Get all blood stocks
    @GetMapping
    public List<BloodStock> getAllBloodStocks() {
        return bloodStockService.getAllBloodStocks();
    }

    // Get a blood stock by ID
    @GetMapping("/{id}")
    public BloodStock getBloodStockById(@PathVariable Integer id) {
        return bloodStockService.getBloodStockById(id);
    }

    // Add a new blood stock
    @PostMapping
    public BloodStock addBloodStock(@RequestBody BloodStock bloodStock) {
        return bloodStockService.addBloodStock(bloodStock);
    }

    // Update a blood stock by ID
    @PutMapping("/{id}")
    public BloodStock updateBloodStock(@PathVariable Integer id, @RequestBody BloodStock bloodStock) {
        return bloodStockService.updateBloodStock(id, bloodStock);
    }

    // Delete a blood stock by ID
    @DeleteMapping("/{id}")
    public void deleteBloodStock(@PathVariable Integer id) {
        bloodStockService.deleteBloodStock(id);
    }
}
