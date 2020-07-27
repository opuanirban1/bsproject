package com.robi.dev.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/*
CREATE TABLE [dbo].[TBL_CEQ_SENT_SMS](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[ORGADDR] [varchar](300) NULL,
	[DESTADDR] [varchar](300) NULL,
	[MSG] [varchar](600) NULL,
	[STATUS] [varchar](300) NULL,
	[FLAG] [varchar](300) NULL,
	[DATETIME] [datetime] NULL,
	[UPDATEDATE] [datetime] NULL,
	[NUM_OF_TRY] [int] NULL
)
 */


@Getter
@Setter
@Data
@Entity
@Table(name = "TBL_CEQ_SENT_SMS")
public class CeqSentSms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String ORGADDR;
    private String DESTADDR;
    private String MSG;
    private String STATUS;
    private String FLAG;
    private Date DATETIME;
    private Date UPDATEDATE;
    private int NUM_OF_TRY;



}
