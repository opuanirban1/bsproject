package com.robi.dev.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Getter
@Setter
@Data
@Entity
@Table(name = "gradewisesalary")

public class Gradewisesalary {

    /*
    	company_grade
	basic_salary

	house_rent
	medical_allowence

	total_salary*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=true, name="company_grade")
    private String company_grade;

    @Min(value=1, message = "Valid Salary amount requerd")
    @Column( /*nullable=true,*/ name="basic_salary")
    private double basic_salary ;


    @Column(nullable=true, name="medical_allowence")
    private double medical_allowence;


    @Column(nullable=true, name="house_rent")
    private double house_rent;

    @Column( nullable=true, name="total_salary")
    private double 	total_salary;



}
