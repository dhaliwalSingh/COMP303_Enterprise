package com.Assignment2.Harshbir_COMP303_Assignment2.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "programs")
public class Program {
    @Id
    @Column(name = "program_code")
    private String programCode;

    @NotBlank(message = "Program name cannot be blank")
    @Column(name = "program_name")
    private String programName;

    @Column(name = "duration")
    private String duration;

    @NotNull(message = "Program fee is required")
    @Positive(message = "Program fee must be positive")
    @Column(name = "fee")
    private Double fee;

    @Column(name = "professor")
    private String professor;

    public Program(){}

    public Program(String programCode, String programName, String duration, Double fee, String professor) {
        this.programCode = programCode;
        this.programName = programName;
        this.duration = duration;
        this.fee = fee;
        this.professor = professor;
    }

    public String getProgramCode(){
        return programCode;
    }
    public void setProgramCode(String programCode){
        this.programCode = programCode;
    }
    public String getProgramName(){
        return programName;
    }
    public void setProgramName(String programName){
        this.programName = programName;
    }
    public String getDuration(){
        return duration;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }
    public Double getFee(){
        return fee;
    }
    public void setFee(Double fee){
        this.fee = fee;
    }
    public String getProfessor(){
        return professor;
    }
    public void setProfessor(String professor){
        this.professor = professor;
    }
}
