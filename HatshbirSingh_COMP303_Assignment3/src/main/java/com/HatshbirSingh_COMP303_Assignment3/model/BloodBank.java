package com.HatshbirSingh_COMP303_Assignment3.model;

import java.util.Objects;

public class BloodBank {

    private Integer id;
    private String bloodbankName;
    private String address;
    private String city;
    private String phone;
    private String email;

    // Default constructor
    public BloodBank() {
    }

    // Parameterized constructor
    public BloodBank(Integer id, String bloodbankName, String address, String city, String phone, String email) {
        this.id = id;
        this.bloodbankName = bloodbankName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getBloodbankName() { return bloodbankName; }
    public void setBloodbankName(String bloodbankName) { this.bloodbankName = bloodbankName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloodBank bloodBank = (BloodBank) o;
        return Objects.equals(id, bloodBank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Override toString
    @Override
    public String toString() {
        return "BloodBank{" +
                "id=" + id +
                ", bloodbankName='" + bloodbankName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
