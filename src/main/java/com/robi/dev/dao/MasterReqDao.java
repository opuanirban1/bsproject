package com.robi.dev.dao;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;

import com.robi.dev.model.MasterReq;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 *Auhtor : Anirban Das
 *Date : 1/5/2020
 *
 */


public interface MasterReqDao extends CrudRepository <MasterReq, Long>{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO TBL_MASTER_REQ (MSISDN,USSD_CODE,STATUS, INSERT_TIME) VALUES ( ?1 , ?2 , ?3, now() )", nativeQuery = true)
    void insertMasterReqInfo (String msisdn, String servicecode, String status);

    @Query(value = "select distinct UN_REG_NUM from TBL_REF_CAM_MASTER where  REG_NUM = ?1", nativeQuery = true)
    ArrayList<String> getUnRegNum (String msisdn);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO TBL_CEQ_SENT_SMS  (ORGADDR ,DESTADDR  ,MSG ,STATUS ,FLAG  ,DATETIME  ,UPDATEDATE, NUM_OF_TRY) VALUES ('1600',  ?1  ,?2 ,'WAIT'  ,'OUT' ,now() ,now() ,0)", nativeQuery = true)
    void insertCeqSentSms (String msisdn,  String msg);
}
