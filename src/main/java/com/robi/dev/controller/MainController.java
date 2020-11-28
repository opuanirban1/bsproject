package com.robi.dev.controller;

import com.robi.dev.dao.BankaccountDao;
import com.robi.dev.dao.GradewiseSalaryDao;
import com.robi.dev.dao.EmployeeDao;
import com.robi.dev.model.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@DynamicUpdate
@Controller
@RequestMapping("/")
public class MainController {


    private final EmployeeDao employeeDao;

    private final GradewiseSalaryDao gradewiseSalaryDao;

    private final BankaccountDao bankaccountDao;




    @Autowired
    public MainController(EmployeeDao employeeDao,GradewiseSalaryDao gradewiseSalaryDao, BankaccountDao bankaccountDao) {
        this.employeeDao = employeeDao;
        this.gradewiseSalaryDao = gradewiseSalaryDao;
        this.bankaccountDao=bankaccountDao;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home()
    {

        return "ussd-show";

    }

    @RequestMapping(value="/employeeshow", method = RequestMethod.GET)
    public String employeeshow(Model model,
                         Employee employee)
    {

        return "addemployee";

    }

    ///employeead
    @RequestMapping(value="/employeeadd", method = RequestMethod.POST)
    public String employeeadd(Model model,
                              @Valid /*@ModelAttribute("employee")*/Employee employee, BindingResult result)
    {

        if (result.hasErrors()||result.hasFieldErrors()) {
            System.out.println ("Employee validation error");
            return "addemployee";
        }
        else {
            employeeDao.insertEmployee(employee.getName(), employee.getAddress(),
                    employee.getMobileno(), employee.getBankaccount(), employee.getSalary_id());
            //return "redirect:list";
            System.out.println("" + employee);
            return "addemployeesuccess";
        }
    }

    @RequestMapping(value="/addsalaryshow", method = RequestMethod.GET)
    public String addsalaryshow(Model model,
                               Gradewisesalary gws)
    {

        return "addsalary";

    }
    ///salaryadd

    //@Transactional
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value="/salaryadd", method = RequestMethod.POST)
    public String  salaryadd(Model model,
                               @Valid Gradewisesalary gws, BindingResult result)
    {

        //return "addsalary";
        List<Integer> gwsl= new ArrayList<>();
        double hr=0.0,ma=0.0,ts=0.0,lgbasicsalary=0.0;
        Integer id =0;
        if (result.hasErrors()||result.hasFieldErrors()) {
            System.out.println ("Addsalary validation error");
            return "addsalary";
        }
        else {
                BankingBasicRule bankbrule = new BankingBasicRule();
                gwsl = gradewiseSalaryDao.getSalaryGradeAll();
                lgbasicsalary = gws.getBasic_salary();
                if (gwsl.size() > 0)
                {

                    for (int counter = 0; counter < gwsl.size(); counter++ ){

                        hr=bankbrule.getHouseRent(lgbasicsalary);
                        ma=bankbrule.getMedicalAllowence(lgbasicsalary);
                        ts = bankbrule.getTotalSalary(lgbasicsalary, bankbrule.getHouseRent(lgbasicsalary),
                                                       bankbrule.getMedicalAllowence(lgbasicsalary));
                        id = gwsl.get(counter);

                        System.out.println("ID:"+id+" Basic slaary:"+lgbasicsalary
                                +" house reant"+hr+ "medical allowence "+ma
                                +"total salary "
                                +ts);

                        gradewiseSalaryDao.updateIDwiseSalary(lgbasicsalary,hr,ma,ts,id);
                        lgbasicsalary = lgbasicsalary +5000;
                    }


                }

                return "addsalarysuccess";

        }

    }

    @RequestMapping(value="/mspaymentshow", method = RequestMethod.GET)
    public String showmapayment(Model model,
                                Bankaccount bacc)
    {

        return "mapayment";

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value="/mspaymentdo", method = RequestMethod.POST)
    public String domapayment(Model model,
                               @Valid Bankaccount bacc, BindingResult result)
    {

        double paymentamount=0.0,finalamount = 0.0, curamount =0.0 ;
        //double finalamount = 0.0;

        if (result.hasErrors()||result.hasFieldErrors()) {
            System.out.println ("MA payment validation error");
            return "mapayment";
        }
        else {
            paymentamount = bacc.getCurrentbalance();
            //curamount = bankaccountDao.getCurBalanceMAcc();
            /*try {
                paymentamount = bacc.getCurrentbalance();
                curamount = bankaccountDao.getCurBalanceMAcc();
                finalamount = (double)(paymentamount+curamount);

            }catch (NullPointerException ex){
                ex.printStackTrace();
                finalamount = (double)(paymentamount+0.00);

            }*/
            BankingBasicRule bbr = new BankingBasicRule();
            finalamount = bbr.addAmountinCurBalance(paymentamount,bankaccountDao.getCurBalanceMAcc());
            bankaccountDao.updateCurBalanceMAcc(finalamount);
            return "mapaymentsuccess";
        }
    }

    @RequestMapping(value="/salarytransshow", method = RequestMethod.GET)
    public String showsalaraytrans(Model model,
                                Bankaccount bacc)
    {

        return "salarytransfer";

    }

    @Transactional
    @RequestMapping(value="/salarytransdo", method = RequestMethod.POST)
    public String dosalaraytrans(ModelMap model,
                                 @Valid  Bankaccount bacc, BindingResult result)
    {
        double maincurbalance=0.0, totalsalary=0.0,reqamount=0.0,ets=0.0,bcb=0.0,
                adam=0.0,totaltransalary=0.0, fmaccvalueaftertrans=0.0;
        String message ="";
        int bid=0;
        List<Double> curbal = new ArrayList<Double>();
        List<Double>  empwts = new ArrayList<Double>();
        List<Integer>  baid = new ArrayList<Integer>();


        maincurbalance = bankaccountDao.getCurBalanceMAcc();
        totalsalary= gradewiseSalaryDao.getTotalSalaryAmount();
       // model.addAttribute("reqamnt", reqamount);
        if (totalsalary>maincurbalance){

            reqamount= (double)(totalsalary-maincurbalance);
            message = "Salary payment failed due to "+reqamount+
                    " amount issue. Add main account balance from Main Account Payment Mebn";
            model.addAttribute("message",message);
            return "salarytransferfail";
        }
        else {

            curbal = bankaccountDao.getAllBankAcountCurBal();
            baid = bankaccountDao.getAllBankAcountID();
            empwts = gradewiseSalaryDao.getAllEmpwiseTotalSalary();
            BankingBasicRule  bnkbrule = new BankingBasicRule();

            if(curbal.size()>0 && empwts.size()>0 && baid.size()>0){


                for(int itr =0 ; itr<curbal.size();itr++){

                    bid=baid.get(itr);
                    ets=empwts.get(itr);
                    bcb = curbal.get(itr);
                    adam= bnkbrule.addAmountinCurBalance(ets,bcb);
                    System.out.println ("ID "+bid+" Total Salary "+ets
                            +" Current Balance "+bcb+" value shoud be updated "+adam);

                    totaltransalary = totaltransalary+adam;

                    bankaccountDao.updateCurBalanceEmpAcc(adam, bid);

                }

                fmaccvalueaftertrans = bnkbrule.removeAmountinCurBalance(totaltransalary,maincurbalance);
                System.out.println ("Total tranfered amount "+totaltransalary+ "cur man account balance "+maincurbalance
                +"value will be updated"+fmaccvalueaftertrans);

                bankaccountDao.updateCurBalanceMAcc(fmaccvalueaftertrans);

            }

            message = "Total paid salary is :"+totaltransalary+" Remaining Main account is :"+fmaccvalueaftertrans;
            model.addAttribute("message",message);
            return "salarytransfersuccess";
        }

    }


}
