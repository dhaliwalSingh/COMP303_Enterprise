package com.Harshbir.FinalTest_COMP303.Controller;

import com.Harshbir.FinalTest_COMP303.entity.Bank;
import com.Harshbir.FinalTest_COMP303.service.BankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banks")
@CrossOrigin(origins = "http://localhost:5173")
public class BankController {

    @Autowired
    private BankService bankService;

    // Add a new bank
    @PostMapping
    public ResponseEntity<Bank> addBank(@Valid @RequestBody Bank bank) {
        Bank createdBank = bankService.addBank(bank);
        return ResponseEntity.ok(createdBank);
    }

    // Get a bank by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        Optional<Bank> bank = bankService.getBankById(id);
        return bank.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get a bank by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Bank> getBankByName(@PathVariable String name) {
        Optional<Bank> bank = bankService.getBankByName(name);
        return bank.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all banks
    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        return ResponseEntity.ok(banks);
    }

    // Delete a bank by ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteBankById(@PathVariable Long id) {
        bankService.deleteBankById(id);
        return ResponseEntity.noContent().build();
    }

    // Delete a bank by name
    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteBankByName(@PathVariable String name) {
        bankService.deleteBankByName(name);
        return ResponseEntity.noContent().build();
    }

    // Update a bank by ID
    @PutMapping("/id/{id}")
    public ResponseEntity<Bank> updateBankById(@PathVariable Long id, @Valid @RequestBody Bank bank) {
        Bank updatedBank = bankService.updateBankById(id, bank);
        return ResponseEntity.ok(updatedBank);
    }

    // Update a bank by name
    @PutMapping("/name/{name}")
    public ResponseEntity<Bank> updateBankByName(@PathVariable String name, @Valid @RequestBody Bank bank) {
        Bank updatedBank = bankService.updateBankByName(name, bank);
        return ResponseEntity.ok(updatedBank);
    }
}
