package com.Assignment2.Harshbir_COMP303_Assignment2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_no")
    private Long applicationNo;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_code", nullable = false)
    private Program program;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date")
    private Date startDate;

    @NotNull(message = "Amount paid is required")
    @Column(name = "amount_paid")
    private Double amountPaid;

    // Constructors, Getters, and Setters
    public Enrollment() { }

    public Enrollment(Long applicationNo, Student student, Program program, Date startDate, Double amountPaid) {
        this.applicationNo = applicationNo;
        this.student = student;
        this.program = program;
        this.startDate = startDate;
        this.amountPaid = amountPaid;
    }

    public Long getApplicationNo(){
        return applicationNo;
    }
    public void setApplicationNo(Long applicationNo){
        this.applicationNo = applicationNo;
    }
    public Student getStudent(){
        return student;
    }
    public void setStudent(Student student){
        this.student = student;
    }
    public Program getProgram(){
        return program;
    }
    public void setProgram(Program program){
        this.program = program;
    }
    public Date getStartDate(){
        return startDate;
    }
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    public Double getAmountPaid(){
        return amountPaid;
    }
    public void setAmountPaid(Double amountPaid){
        this.amountPaid = amountPaid;
    }
}
