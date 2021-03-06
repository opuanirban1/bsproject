package com.robi.dev.dao;


import com.robi.dev.dto.EmployeeSalaryDTO;
import com.robi.dev.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 *Auhtor : Anirban Das
 *Date : 11/27/2020
 *
 */


public interface EmployeeDao extends CrudRepository <Employee, Long>{
    /*@Modifying
    @Transactional
    @Query(value = "INSERT INTO TBL_MASTER_REQ (MSISDN,USSD_CODE,STATUS, INSERT_TIME) VALUES ( ?1 , ?2 , ?3, now() )", nativeQuery = true)
    void insertMasterReqInfo (String msisdn, String servicecode, String status);

    @Query(value = "select distinct UN_REG_NUM from TBL_REF_CAM_MASTER where  REG_NUM = ?1", nativeQuery = true)
    ArrayList<String> getUnRegNum (String msisdn);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO TBL_CEQ_SENT_SMS  (ORGADDR ,DESTADDR  ,MSG ,STATUS ,FLAG  ,DATETIME  ,UPDATEDATE, NUM_OF_TRY) VALUES ('1600',  ?1  ,?2 ,'WAIT'  ,'OUT' ,now() ,now() ,0)", nativeQuery = true)
    void insertCeqSentSms (String msisdn,  String msg);*/


    /*@Query(value = "SELECT * FROM gardewisesalary order by ID ASC", nativeQuery = true)
    ArrayList<Gradewisesalary> getSalaryGradeAll();
    */



    @Query(value = "UPDATE gradewisesalary SET basic_salary=?1 WHERE id=1", nativeQuery = true)
    void updateLowGradeSalary (double basicsalary);



    //void updateIDwiseSalary (double basicsalary,Integer id);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO employee  (name, address,mobileno, bankaccount, salary_id,status) " +
            "VALUES (?1,?2,?3,?4,?5 ,1)", nativeQuery = true)
    void insertEmployee (String name,String address, String mobileno,String bankaccount, int salary_id);


   // @Query (value="SELECT  new com.robi.dev.dto.EmployeeSalaryDTO(e.name, s.total_salary) FROM Employee e JOIN Gradewisesalary s  ON e.salary_id = s.id")
   @Query (value="SELECT  new com.robi.dev.dto.EmployeeSalaryDTO(" +
           "e.name, " +
           "e.address,"+
           "e.mobileno ,"+
           "e.bankaccount,"+
           "s.company_grade,"+
           "s.basic_salary ,"+
            "s.medical_allowence,"+
            "s.house_rent,"+
            "s.total_salary, b.id, b.currentbalance) FROM Employee e, Gradewisesalary s, Bankaccount b  WHERE e.salary_id = s.id " +
           "AND e.bankaccount=b.accountnumber AND b.accounttype = 'savings_current'  ORDER BY e.id")
   List<EmployeeSalaryDTO> getEmployeeSarayDTOAll();

    @Transactional
    @Query(value = "SELECT sum(s.total_salary) as total_salary FROM employee e , gradewisesalary s  WHERE e.salary_id=s.id", nativeQuery = true)
    Double getTotalSalaryAmount();
}
