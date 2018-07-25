package com.epam;


import com.epam.constants.Constants;
import com.epam.parsers.TarifSAXParcer;
import com.epam.parsers.TariffStAXParcer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    public static void main(String [] args){


           TarifSAXParcer tarifSAXParcer =new TarifSAXParcer();
        try {
            tarifSAXParcer.parse(Constants.FILENAME_XML);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        TariffStAXParcer tariffStAXParcer = new TariffStAXParcer();
        try {
            tariffStAXParcer.parse(Constants.FILENAME_XML);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }*/
    }

}
