package com.epam.parsers;


import com.epam.model.Tariff;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {
    public final static Logger log = Logger.getLogger(MyHandler.class);
    private List<Tariff> tariffList = null;
    private Tariff tariff = null;

    public List<Tariff> getTariffList() {
        return tariffList;
    }

    boolean bname = false;
    boolean boperatorname = false;
    boolean bpayroll = false;
    boolean bpricesms = false;
    boolean binsidecallprice = false;
    boolean boutsidecallprice = false;
    boolean bstationarycallprice = false;
    boolean bhadfavouritenumber = false;
    boolean btariffication = false;
    boolean bconnectionprice = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (tariffList == null)
            tariffList = new ArrayList<>();

        else if (qName.equalsIgnoreCase("NAME")) {
            bname = true;
        } else if (qName.equalsIgnoreCase("OPERATORNAME")) {
            boperatorname = true;
        } else if (qName.equalsIgnoreCase("PAYROLL")) {
            bpayroll = true;
        } else if (qName.equalsIgnoreCase("PRICESMS")) {
            bpricesms = true;
        } else if (qName.equalsIgnoreCase("INSIDECALLPRICE")) {
            binsidecallprice = true;
        } else if (qName.equalsIgnoreCase("OUTSIDECALLPRICE")) {
            boutsidecallprice = true;
        } else if (qName.equalsIgnoreCase("STATIONARYCALLPRICE")) {
            boutsidecallprice = true;
        } else if (qName.equalsIgnoreCase("HADFAVOURITENUMBER")) {
            bhadfavouritenumber = true;
        } else if (qName.equalsIgnoreCase("TARIFFICATION")) {
            btariffication = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Tariff")) {
            tariffList.add(tariff);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bname) {
            log.info("name : " + new String(ch, start, length));
            bname = false;
        } else if (boperatorname) {
            log.info("operatorName : " + new String(ch, start, length));
            boperatorname = false;
        } else if (bpayroll) {
            log.info("payroll: " + new String(ch, start, length));
            bpayroll = false;
        } else if (bpricesms) {
            log.info("priceSMS : " + new String(ch, start, length));
            bpricesms = false;
        } else if (binsidecallprice) {
            log.info("insideCallPrice : " + new String(ch, start, length));
            binsidecallprice = false;
        } else if (boutsidecallprice) {
            log.info("outsideCallPrice : " + new String(ch, start, length));
            boutsidecallprice = false;
        } else if (bstationarycallprice) {
            log.info("stationaryCallPrice : " + new String(ch, start, length));
            bstationarycallprice = false;
        } else if (bhadfavouritenumber) {
            log.info("hadfavouritenumber : " + new String(ch, start, length));
            bhadfavouritenumber = false;
        } else if (btariffication) {
            log.info("tarrification : " + new String(ch, start, length));
            btariffication = false;
        } else if (bconnectionprice) {
            log.info("connectionprice : " + new String(ch, start, length));
            bconnectionprice = false;
        }
    }
}

