package com.robi.dev.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*CREATE TABLE [dbo].[TBL_REF_CAM_MASTER](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[REG_NUM] [varchar](100) NULL,
	[UN_REG_NUM] [varchar](100) NULL
)
*/

@Getter
@Setter
@Data
@Entity
@Table(name = "TBL_REF_CAM_MASTER")
public class RefCamMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String REG_NUM;
    private String UN_REG_NUM;

}
