package com.robi.dev.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;

import com.robi.dev.model.Gradewisesalary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface GradewiseSalaryDao extends CrudRepository <Gradewisesalary, Long> {

    /*house_rent
		medical_allowence

		total_salary*/

    @Query(value = "SELECT id FROM gradewisesalary order by id ASC", nativeQuery = true)
    List<Integer> getSalaryGradeAll();


    @Modifying
    @Transactional
    /*@Query(value = "UPDATE gradewisesalary SET basic_salary=?1 , house_rent=?2, " +
            " medical_allowence=?3, total_salary=?4  " +
            "WHERE id=?5", nativeQuery = true)*/
    @Query(value = "UPDATE gradewisesalary SET basic_salary=?1, house_rent=?2, medical_allowence=?3, total_salary=?4  WHERE id=?5", nativeQuery = true)
    /*void updateIDwiseSalary (double basicsalary,double house_rent,
                             double medical_allowence, double total_salary, Integer id);*/
    void updateIDwiseSalary (double basicsalary, double house_rent, double medical_allowence, double total_salary, Integer id);



    @Transactional
    @Query(value="SELECT   g.total_salary as total_salary FROM employee e, bankaccount b , gradewisesalary g \n" +
            "WHERE e.salary_id= g.id AND e.bankaccount=b.accountnumber AND b.accounttype = 'savings_current' ORDER BY e.id ASC", nativeQuery = true)
    List<Double> getAllEmpwiseTotalSalary();

}


