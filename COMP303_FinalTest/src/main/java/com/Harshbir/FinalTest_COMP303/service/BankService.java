package com.Harshbir.FinalTest_COMP303.service;

import com.Harshbir.FinalTest_COMP303.entity.Bank;
import com.Harshbir.FinalTest_COMP303.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    // Add a new bank
    public Bank addBank(Bank bank) {
        return bankRepository.save(bank);
    }

    // Get a bank by ID
    public Optional<Bank> getBankById(Long id) {
        return bankRepository.findById(id);
    }

    // Get a bank by name
    public Optional<Bank> getBankByName(String name) {
        return bankRepository.findByBankName(name);
    }

    // Get all banks
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    // Delete a bank by ID
    public void deleteBankById(Long id) {
        bankRepository.deleteById(id);
    }

    // Delete a bank by name
    public void deleteBankByName(String name) {
        bankRepository.deleteByBankName(name);
    }

    // Update a bank by ID
    public Bank updateBankById(Long id, Bank updatedBank) {
        return bankRepository.findById(id).map(bank -> {
            bank.setBankName(updatedBank.getBankName());
            bank.setBankYear(updatedBank.getBankYear());
            bank.setBankEmp(updatedBank.getBankEmp());
            bank.setBankAddress(updatedBank.getBankAddress());
            bank.setBankBranches(updatedBank.getBankBranches());
            bank.setBankATMs(updatedBank.getBankATMs());
            return bankRepository.save(bank);
        }).orElseThrow(() -> new RuntimeException("Bank not found with ID " + id));
    }

    // Update a bank by name
    public Bank updateBankByName(String name, Bank updatedBank) {
        return bankRepository.findByBankName(name).map(bank -> {
            bank.setBankName(updatedBank.getBankName());
            bank.setBankYear(updatedBank.getBankYear());
            bank.setBankEmp(updatedBank.getBankEmp());
            bank.setBankAddress(updatedBank.getBankAddress());
            bank.setBankBranches(updatedBank.getBankBranches());
            bank.setBankATMs(updatedBank.getBankATMs());
            return bankRepository.save(bank);
        }).orElseThrow(() -> new RuntimeException("Bank not found with name " + name));
    }
}