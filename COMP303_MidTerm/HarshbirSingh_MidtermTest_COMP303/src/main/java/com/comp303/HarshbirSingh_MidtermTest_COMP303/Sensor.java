package com.comp303.HarshbirSingh_MidtermTest_COMP303;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensorid")
    private int sensorID;  // Changed to Integer to handle nulls effectively

    @NotBlank(message = "Sensor name cannot be blank")
    @Column(name = "sensorname")
    private String sensorName;

    @NotBlank(message = "Sensor Type is mandatory")
    @Column(name = "Sensortype")
    private String sensorType;

    @NotNull(message = "Sensor pin must be provided")
    @Min(value = 0, message = "Sensor pin must be between 0 and 1000")
    @Max(value = 1000, message = "Sensor pin must be between 0 and 1000")
    @Column(name = "sensorpin")
    private int sensorPin;

    @Column(name = "sensorlocation")
    private String sensorLocation;

    @NotBlank(message = "Sensor status must be specified as ON or OFF")
    @Column(name = "sensorstatus")
    private String sensorStatus;

    public Sensor(){

    }

    public Sensor(int sensorID, String sensorName, String sensorType, int sensorPin, String sensorLocation, String sensorStatus){
        super();
        this.sensorID = sensorID;
        this.sensorName = sensorName;
        this.sensorType = sensorType;
        this.sensorPin = sensorPin;
        this.sensorLocation = sensorLocation;
        this.sensorStatus = sensorStatus;
    }

    public int getSensorID(){
        return sensorID;
    }
    public void setSensorID(int sensorID){
        this.sensorID = sensorID;
    }
    public String getSensorName(){
        return sensorName;
    }
    public void setSensorName(String sensorName){
        this.sensorName = sensorName;
    }
    public String getSensorType(){
        return sensorType;
    }
    public void setSensorType(String sensorType){
        this.sensorType = sensorType;
    }
    public int getSensorPin(){
        return sensorPin;
    }
    public void setSensorPin(int sensorPin){
        this.sensorPin = sensorPin;
    }
    public String getSensorLocation(){
        return sensorLocation;
    }
    public void setSensorLocation(String sensorLocation){
        this.sensorLocation = sensorLocation;
    }
    public String getSensorStatus(){
        return sensorStatus;
    }
    public void setSensorStatus(String sensorStatus){
        this.sensorStatus = sensorStatus;
    }
}

