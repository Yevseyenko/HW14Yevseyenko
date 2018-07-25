package com.epam.parsers;

import com.epam.constants.Constants;
import com.epam.model.Tariff;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLStAXParcer {
    public static void main(String[] args) {

        List<Tariff> tariffList = parseXML(Constants.FILENAME_XML);
        for(Tariff tariff : tariffList){
            System.out.println(tariffList);
        }
    }

    private static List<Tariff> parseXML(String fileName) {
        List<Tariff> tariffList = new ArrayList<>();
        Tariff tariff = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(Constants.FILENAME_XML));
            while(xmlEventReader.hasNext()){
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()){
                    StartElement startElement = xmlEvent.asStartElement();
                    if(startElement.getName().getLocalPart().equals("mobile_tariff")){
                        tariff = new Tariff();
                        //Get the 'id' attribute from Employee element
                        Attribute nameAttr = startElement.getAttributeByName(new QName("name"));
                        if(nameAttr != null){
                            tariff.setName(nameAttr.getValue());
                        }
                    }
                    //set the other varibles from xml elements
                    else if(startElement.getName().getLocalPart().equals("name")){
                        xmlEvent = xmlEventReader.nextEvent();
                       tariff.setName(xmlEvent.asCharacters().getData());
                    }else if(startElement.getName().getLocalPart().equals("operatorName")){
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setOperatorName(xmlEvent.asCharacters().getData());
                    }else if(startElement.getName().getLocalPart().equals("payroll")){
                        xmlEvent = xmlEventReader.nextEvent();
                       tariff.setPayroll(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    }else if(startElement.getName().getLocalPart().equals("priceSMS")){
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setPriceSMS(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    }
                }
                //if Employee end element is reached, add employee object to list
                if(xmlEvent.isEndElement()){
                    EndElement endElement = xmlEvent.asEndElement();
                    if(endElement.getName().getLocalPart().equals("Tariff")){
                        tariffList.add(tariff);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return tariffList;
    }
}
