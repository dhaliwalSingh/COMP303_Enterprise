package com.HatshbirSingh_COMP303_Assignment3.model;

import java.util.Objects;

public class Seeker {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String bloodGroup;
    private String city;
    private String phone;

    public Seeker() {

    }

    public Seeker(Integer id, String firstName, String lastName, Integer age, String bloodGroup, String city, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.city = city;
        this.phone = phone;
    }

    // Getters and Setters for each field
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Override equals and hashCode for proper comparison and hashing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seeker seeker = (Seeker) o;
        return Objects.equals(id, seeker.id) &&
                Objects.equals(firstName, seeker.firstName) &&
                Objects.equals(lastName, seeker.lastName) &&
                Objects.equals(age, seeker.age) &&
                Objects.equals(bloodGroup, seeker.bloodGroup) &&
                Objects.equals(city, seeker.city) &&
                Objects.equals(phone, seeker.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, bloodGroup, city, phone);
    }

    // Override toString for meaningful output when printing Seeker instances
    @Override
    public String toString() {
        return "Seeker{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}