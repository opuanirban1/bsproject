package com.robi.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeSalaryDTO {
 //accounttype		accountnumber	currentbalance	bankname	bankbranch
    private String name;
    private String address ;
    private String mobileno ;
    private String bankaccount;
    private String company_grade;
    private double basic_salary ;
    private double medical_allowence;
    private double house_rent;
    private double 	total_salary;
    private long   id;
    private double currentbalance;


}
