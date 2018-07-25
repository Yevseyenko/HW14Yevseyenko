package com.epam.parsers;

import com.epam.constants.Constants;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLDomParser {
    public final static Logger log = Logger.getLogger(XMLDomParser.class);

    public static void main(String[] args) {
        try {
            File fXmlFile = new File(Constants.FILENAME_XML);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("mobile_tariff");

            NodeList nlist1 = doc.getElementsByTagName("parameters");
            NodeList nlist2 = doc.getElementsByTagName("callprices");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                Node nNode2 = nlist1.item(temp);
                Node nNode3 = nlist2.item(temp);
                log.info("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    Element element = (Element) nNode2;
                    Element insideElement = (Element) nNode3;
                    log.info("name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    log.info("operatorName : " + eElement.getElementsByTagName("operatorName").item(0).getTextContent());
                    log.info("payroll : " + eElement.getElementsByTagName("payroll").item(0).getTextContent());
                    log.info("priceSMS : " + eElement.getElementsByTagName("priceSMS").item(0).getTextContent());
                    log.info("parameters : ");
                    log.info("hadFavouriteNumber : " + element.getElementsByTagName("hadFavouriteNumber").item(0).getTextContent());
                    log.info("tariffication : " + element.getElementsByTagName("tariffication").item(0).getTextContent());
                    log.info("connectionPrice : " + element.getElementsByTagName("connectionPrice").item(0).getTextContent());
                    log.info("callprices : ");
                    log.info("insideCallPrice : " + insideElement.getElementsByTagName("insideCallPrice").item(0).getTextContent());
                    log.info("outsideCallPrice : " + insideElement.getElementsByTagName("outsideCallPrice").item(0).getTextContent());
                    log.info("stationaryCallPrice : " + insideElement.getElementsByTagName("stationaryCallPrice").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


