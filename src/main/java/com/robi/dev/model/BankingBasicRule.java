package com.robi.dev.model;

public class BankingBasicRule {

    public BankingBasicRule(){
    }

    public  double getHouseRent (double basicsalary){

        return ((20*basicsalary)/100);
    }

    public  double getMedicalAllowence (double basicsalary){

        return ((15.0*basicsalary)/100.0);
    }

    public double getTotalSalary (double bsalary, double hrent, double mallowence){

        return (bsalary+hrent+mallowence);


    }

    public double addAmountinCurBalance (double paymentamount, double curbalance){

        double finalamount = 0.0;

        finalamount = (double)(paymentamount+curbalance);

        return finalamount;

    }

    public double removeAmountinCurBalance (double paymentamount, double curbalance){

        double finalamount = 0.0;

        finalamount = (double)(curbalance-paymentamount);

        return finalamount;

    }
}
