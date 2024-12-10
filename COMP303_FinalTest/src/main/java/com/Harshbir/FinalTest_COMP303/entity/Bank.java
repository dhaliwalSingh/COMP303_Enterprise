package com.Harshbir.FinalTest_COMP303.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankID;

    @NotBlank(message = "Bank name is mandatory")
    private String bankName;

    @NotNull(message = "Establishment year is mandatory")
    private Integer bankYear;

    @NotNull(message = "Number of employees is mandatory")
    @Min(value = 1, message = "Number of employees must be at least 1")
    private Integer bankEmp;

    @NotBlank(message = "Bank address is mandatory")
    private String bankAddress;

    @NotNull(message = "Number of branches is mandatory")
    @Min(value = 1, message = "Number of branches must be at least 1")
    private Integer bankBranches;

    @NotNull(message = "Number of ATMs is mandatory")
    @Min(value = 0, message = "Number of ATMs cannot be negative")
    private Integer bankATMs;

    // Getters and Setters

    public Long getBankID() {
        return bankID;
    }

    public void setBankID(Long bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getBankYear() {
        return bankYear;
    }

    public void setBankYear(Integer bankYear) {
        this.bankYear = bankYear;
    }

    public Integer getBankEmp() {
        return bankEmp;
    }

    public void setBankEmp(Integer bankEmp) {
        this.bankEmp = bankEmp;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public Integer getBankBranches() {
        return bankBranches;
    }

    public void setBankBranches(Integer bankBranches) {
        this.bankBranches = bankBranches;
    }

    public Integer getBankATMs() {
        return bankATMs;
    }

    public void setBankATMs(Integer bankATMs) {
        this.bankATMs = bankATMs;
    }
}
