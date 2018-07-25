package com.epam.parsers;

import com.epam.constants.Constants;
import com.epam.model.Callprices;
import com.epam.model.Parameters;
import com.epam.model.Tariff;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLDomParser {
    public final static Logger log = Logger.getLogger(XMLDomParser.class);

    public static void main(String[] args) {
        File xmlFile = new File(Constants.FILENAME_XML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            log.info("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("mobile_tariff");
            NodeList nodeList1 = doc.getElementsByTagName("parameters");
            NodeList nodeList2 = doc.getElementsByTagName("callprices");
            List<Tariff> tariffList = new ArrayList<Tariff>();
            List<Parameters> parametersList = new ArrayList<Parameters>();
            List<Callprices> callpricesList = new ArrayList<Callprices>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                tariffList.add(getTariff(nodeList.item(i)));
                parametersList.add(getparameters(nodeList1.item(i)));
                callpricesList.add(getCallprices(nodeList2.item(i)));
            }

            for (int i = 0; i < tariffList.size(); i++) {

                log.info(tariffList.get(i).toString() + " " + parametersList.get(i).toString() + " " + callpricesList.get(i).toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }

    }

    private static Tariff getTariff(Node node1) {
        Tariff tariff = new Tariff();
        if (node1.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node1;
            tariff.setName(getTagValue("name", element));
            tariff.setOperatorName(getTagValue("operatorName", element));
            tariff.setPayroll(Double.parseDouble(getTagValue("payroll", element)));
            tariff.setPriceSMS(Double.parseDouble(getTagValue("priceSMS", element)));

        }
        return tariff;
    }

    private static Parameters getparameters(Node node2) {
        Parameters parameters = new Parameters();
        if (node2.getNodeType() == Node.ELEMENT_NODE) {
            Element element2 = (Element) node2;
            parameters.setTariffication(getTagValue("tariffication", element2));
            parameters.setConnectionPrice(Double.parseDouble(getTagValue("connectionPrice", element2)));
            parameters.setHadFavouriteNumber(Boolean.parseBoolean(getTagValue("hadFavouriteNumber", element2)));

        }
        return parameters;
    }

    private static Callprices getCallprices(Node node3) {
        Callprices callprices = new Callprices();

        if (node3.getNodeType() == Node.ELEMENT_NODE) {
            Element element3 = (Element) node3;
            callprices.setInsideCallPrice(Double.parseDouble(getTagValue("insideCallPrice", element3)));
            callprices.setOutsideCallPrice(Double.parseDouble(getTagValue("outsideCallPrice", element3)));
            callprices.setStationaryCallPrice(Double.parseDouble(getTagValue("stationaryCallPrice", element3)));
        }
        return callprices;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}



