package com.epam.parsers;

import com.epam.constants.Constants;
import com.epam.model.Tariff;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLSaxParser {
    public final static Logger log = Logger.getLogger(XMLSaxParser.class);

    public static void main(String args[]) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse(new File(Constants.FILENAME_XML), handler);
            //Get Employees list
            List<Tariff> tariffList = handler.getTariffList();
            //print employee information
            for (Tariff tariff : tariffList)
                log.info(tariff);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
