package com.robi.dev.controller;

import com.robi.dev.dao.MasterReqDao;
import com.robi.dev.model.EtsafInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private MasterReqDao masterreqdao;

    @RequestMapping(value="/application_uri", method = RequestMethod.GET)
    public String home(Model model,
                       @RequestParam("userid") String userid,
                       @RequestParam("password") String password,
                       @RequestParam("msisdn") String msisdn,
                       @RequestParam("session") String session,
                       @RequestParam("servicecode") String servicecode,
                       @RequestParam("input") String input,
                       HttpServletResponse response)
    {
        //List<EmployeeEntity> list = service.getAllEmployees();
        //String name = "opu";
        //model.addAttribute("opu", name);

        String res="", checkcusinfo="";
        EtsafInfo etinfo = new EtsafInfo();

        response.setHeader("Freeflow", "FC"); // adding header with FC value
        response.setHeader("charge", "N"); // charge no
        response.setHeader("amount", "0");// amount 0

        if (userid.equals("appid1") && password.equals("apppwd123") ) {

            if (servicecode.equals("1600") && input.equals("1600")) {

                response.setHeader("Freeflow", "FB");

                /*"3. Mobile Number count check.<br>" +*/
                res = "Please select from below options:<br>" +
                        "1. Re-registration Status Check.<br>" +
                        "2. Biometric POS Location.<br>" +
                        "3. Your referral numbers.";

            } else if (servicecode.equals("1600") && (input.equals("1") || input.equals("1600*1"))) {

                response.setHeader("Freeflow", "FB");

                checkcusinfo = etinfo.etsafAPICustomerInfo(msisdn);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDateTime now = LocalDateTime.now();
                // System.out.println(dtf.format(now));


                if (checkcusinfo.equals("")){


                    res =  "Dear Customer ("+msisdn+"), as of  "+dtf.format(now)+", registration is not completed." +
                            "Press *1600*2# to know the location of nearest Biometric  Registration Point.";

                }
                else{

                    res = "Dear Customer ("+msisdn+"), your re-registration has been completed on "+checkcusinfo+".";

                }


            } else if (servicecode.equals("1600") && (input.equals("2") || input.equals("1600*2"))) {

                response.setHeader("Freeflow", "FB");

                long nowtime = (System.currentTimeMillis()%10);

                if (nowtime ==0){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT");

                } else if (nowtime == 1){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT1");

                } else if (nowtime == 2){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT2");

                } else if (nowtime == 3){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT3");

                } else if (nowtime == 4){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT4");

                } else if (nowtime == 5){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT5");

                } else if (nowtime == 6){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT6");

                } else if (nowtime == 7){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT7");

                } else if (nowtime == 8){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT8");

                } else if (nowtime == 9){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT9");

                } else if (nowtime == 9){

                    masterreqdao.insertMasterReqInfo(msisdn,servicecode,"WAIT9");

                }

                res = "Dear Customer, your request has been captured. We will inform you the Retail location shortly. Please wait.";




            } else if (servicecode.equals("1600") && (input.equals("3") || input.equals("1600*3"))) {

                response.setHeader("Freeflow", "FB");

                String rt_msisdn = "",msisdnlist="" , sms ="", smsmsisdn="";
                rt_msisdn = msisdn.substring(2, msisdn.length());
                ArrayList<String> unregnum = new  ArrayList<String>();

                unregnum = masterreqdao.getUnRegNum(rt_msisdn);

                if (unregnum.size() > 0) {
                    for (int i = 0; i < unregnum.size(); i++) {

                        msisdnlist += unregnum.get(i)+"<br>";
                        smsmsisdn += unregnum.get(i)+" ";
                    }

                    res = "Call the users of these numbers to complete re-reg & win 5GB FREE: <br>"+msisdnlist;
                    sms = "Call the users of these numbers to complete re-reg & win 5GB FREE: "+smsmsisdn;
                }
                else{
                    res= "No referral number found.";
                    sms = res;
                }

                masterreqdao.insertCeqSentSms(msisdn,sms);


            }  else{

                response.setHeader("Freeflow", "FB");  // amount 0
                res = "Invalid input.";

            }

        }else{
            // header('Freeflow: FB');
            response.setHeader("Freeflow", "FB");  // amount 0
            res = "Authentication Failed.";
        }



        model.addAttribute("result", res);
        System.out.println("Output :"+res);
        return "ussd-show";
    }


}
