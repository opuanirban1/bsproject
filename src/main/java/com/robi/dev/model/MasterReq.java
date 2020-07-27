package com.robi.dev.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Data
@Entity
@Table(name = "TBL_MASTER_REQ")
public class MasterReq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String MSISDN;
    private String USSD_CODE;
    private String STATUS;
    private Date INSERT_TIME;

}
