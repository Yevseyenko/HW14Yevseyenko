package com.epam.model;

public class Tariff {
    public String name;
    public String operatorName;
    public double priceSMS;
    public double payroll;
    public Callprices callprices ;
    public Parameters parameters;
    public Tariff(){}
    public Tariff(String name, String operatorName, double priceSMS, double payroll, Callprices callprices, Parameters parameters){
        this.name=name;
        this.operatorName =operatorName;
        this.priceSMS= priceSMS;
        this.payroll=payroll;
        this.callprices=callprices;
        this.parameters=parameters;
    }

    public String getName() {
        return name;
    }

    public double getPayroll() {
        return payroll;
    }

    public double getPriceSMS() {
        return priceSMS;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public void setPayroll(double payroll) {
        this.payroll = payroll;
    }

    public void setPriceSMS(double priceSMS) {
        this.priceSMS = priceSMS;
    }

    public Callprices getCallPrices() {
        return callprices;
    }

    public Parameters getParameters() {
        return parameters;
    }
}
