package com.epam;

import com.epam.constants.Constants;
import com.epam.model.Tariff;
import com.epam.parsers.TarifDomParcer;
import com.epam.parsers.TarifSAXParcer;
import com.epam.parsers.TariffStAXParcer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Arrays;

import static com.epam.parsers.TarifDomParcer.log;

public class Main {
    public static void main(String [] args){

            TarifDomParcer tarifDomParcer =new TarifDomParcer();
           log.info(tarifDomParcer.parseDom(Constants.FILENAME_XML));
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

        TariffStAXParcer tariffStAXParcer = new TariffStAXParcer();
        try {
            tariffStAXParcer.parse(Constants.FILENAME_XML);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
