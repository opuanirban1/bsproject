package com.robi.dev.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;


@Getter
@Setter
@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotBlank(message = "Name is mandatory.")
    @Column(/*nullable=true,*/ name="name")
    //@NotNull(message = "Address is mandatory.")
    private String name;

    @NotBlank(message = "Address is mandatory.")
    @Column( /*nullable=true, */name="address")
    private String address ;


    @Column(/*nullable=true,*/ name="mobileno")
    @Size(min = 10, max = 15 , message = "Mobile no must be betwwen 10 to 15 character lenght")
    private String mobileno ;


    @NotBlank(message = "Bank account is mandatory.")
    @Column(/*llable=true,*/ name="bankaccount")
    private String bankaccount;

    @Min(value=1,message = "Min value is 1")
    @Max(value=6,message = "Max value is 6")
    @Column(/*nullable=true, */name="salary_id")
    private int salary_id;
}
