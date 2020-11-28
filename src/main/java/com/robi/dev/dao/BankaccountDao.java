package com.robi.dev.dao;


import com.robi.dev.model.Bankaccount;
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


public interface BankaccountDao extends CrudRepository <Bankaccount, Long>{

    //accounttype 		accountname 	accountnumber currentbalance  	bankname 		bankbranch
    @Modifying
    @Transactional
    @Query(value = "UPDATE bankaccount SET currentbalance=?1  WHERE accounttype ='mainaccount' AND accountnumber= '100' AND id =1", nativeQuery = true)
    void updateCurBalanceMAcc (double finalamount);

    @Transactional
    @Query(value = "SELECT currentbalance FROM bankaccount WHERE accounttype ='mainaccount' AND accountnumber= '100' AND id =1 ", nativeQuery = true)
    Double getCurBalanceMAcc();


    //, g.total_salary as total_salary, b.currentbalance as  house_rent
    /*SELECT b.id as id, g.total_salary as total_salary, b.currentbalance as currentbalance FROM employee e, bankaccount b , gradewisesalary g WHERE e.salary_id= g.id AND e.bankaccount=b.accountnumber AND b.accounttype = 'savings_current' ORDER BY b.id ASC*/
    @Transactional
    @Query(value="SELECT b.id as id FROM employee e, bankaccount b WHERE  e.bankaccount=b.accountnumber AND b.accounttype = 'savings_current' ORDER BY e.id ASC", nativeQuery = true)
    List<Integer> getAllBankAcountID();

    @Transactional
    @Query(value="SELECT b.currentbalance as currentbalance FROM employee e, bankaccount b  WHERE  e.bankaccount=b.accountnumber AND b.accounttype = 'savings_current' ORDER BY e.id ASC", nativeQuery = true)
    List<Double> getAllBankAcountCurBal();

    @Modifying
    @Transactional
    @Query(value = "UPDATE bankaccount SET currentbalance=?1  WHERE accounttype ='savings_current'  AND id =?2", nativeQuery = true)
    void updateCurBalanceEmpAcc (double finalamount,int id);





}
