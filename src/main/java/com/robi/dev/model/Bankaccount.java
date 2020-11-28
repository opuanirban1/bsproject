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
@Table(name = "bankaccount")

public class Bankaccount {

    /*accounttype 		accountname 	accountnumber currentbalance  	bankname 		bankbranch*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=true, name="accounttype")
    private String accounttype;


    @Column( nullable=true, name="accountname")
    private String accountname ;


    @Column(nullable=true, name="accountnumber")
    private String accountnumber;

    @Min(value=1, message = "Valid payment amount requerd.")
    @Column(/*nullable=true,*/ name="currentbalance")
    private double currentbalance ;

    @Column( nullable=true, name="bankname")
    private String 	bankname ;

    @Column( nullable=true, name="bankbranch")
    private String 	bankbranch ;


}
