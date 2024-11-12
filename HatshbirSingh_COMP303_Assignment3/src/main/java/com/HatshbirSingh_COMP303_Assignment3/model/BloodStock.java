package com.HatshbirSingh_COMP303_Assignment3.model;

import java.time.LocalDate;
import java.util.Objects;

public class BloodStock {

    private Integer id;
    private String bloodGroup;
    private Integer quantity;
    private LocalDate bestBefore;
    private String status;

    // Default constructor
    public BloodStock() {
    }

    // Parameterized constructor
    public BloodStock(Integer id, String bloodGroup, Integer quantity, LocalDate bestBefore, String status) {
        this.id = id;
        this.bloodGroup = bloodGroup;
        this.quantity = quantity;
        this.bestBefore = bestBefore;
        this.status = status;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public LocalDate getBestBefore() { return bestBefore; }
    public void setBestBefore(LocalDate bestBefore) { this.bestBefore = bestBefore; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloodStock that = (BloodStock) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Override toString
    @Override
    public String toString() {
        return "BloodStock{" +
                "id=" + id +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", quantity=" + quantity +
                ", bestBefore=" + bestBefore +
                ", status='" + status + '\'' +
                '}';
    }
}