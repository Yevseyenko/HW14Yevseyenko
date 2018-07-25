package com.epam.parsers;

import com.epam.model.Callprices;
import com.epam.model.Parameters;
import com.epam.model.Tariff;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TarifDomParcer {


        public ArrayList<Tariff> tariffs = new ArrayList<Tariff>();
        public final static Logger log = Logger.getLogger(TarifDomParcer.class);

        public ArrayList<Tariff> parseDom(String xmlFileName) {
            try{
                log.info("Parsing XML with DOM pareser");
                DOMParser domParser = new DOMParser();
                domParser.parse(xmlFileName);
                Document document = domParser.getDocument();
                Element root = document.getDocumentElement();
                analyze(root);
            } catch (SAXException e) {
                log.error("XML parsing error: " + e.getMessage());
            } catch (IOException e) {
                log.error("IO error: " + e.getMessage());
            }
            return tariffs;
        }

        public void analyze(Element root) {
            NodeList tariffList = root.getElementsByTagName("tariff");
            for(int i = 0; i < tariffList.getLength(); i++){
                Element tariffElement = (Element) tariffList.item(i);
                Tariff tariff = buildTariff(tariffElement);
                tariffs.add(tariff);
            }
        }
        public Tariff buildTariff(Element tariffElement) {
            Tariff tariff = new Tariff();
            tariff.setName(getElementTextContent(tariffElement, "name"));
            tariff.setOperatorName(getElementTextContent(tariffElement, "operatorName"));
            tariff.setPayroll(Double.parseDouble(getElementTextContent(tariffElement, "payroll")));
            tariff.setPriceSMS(Double.parseDouble(getElementTextContent(tariffElement, "smsPrice")));

            Callprices callPrice = tariff.getCallPrices();
            Element elementCallPrice = (Element) tariffElement.getElementsByTagName("callPrice").item(0);
            callPrice.setInsideCallPrice(Double.parseDouble(getElementTextContent(elementCallPrice, "intraCallPrice")));
            callPrice.setOutsideCallPrice(Double.parseDouble(getElementTextContent(elementCallPrice, "externalCallPrice")));
            callPrice.setStationaryCallPrice(Double.parseDouble(getElementTextContent(elementCallPrice, "landLineCallPrice")));

            Parameters parameter = tariff.getParameters();
            Element elementParameter = (Element) tariffElement.getElementsByTagName("parameter").item(0);
            parameter.setHadFavouriteNumber(Boolean.parseBoolean(getElementTextContent(elementParameter, "hasFavouriteNumber")));
            parameter.setTariffication(getElementTextContent(elementParameter, "typeTariff"));
            parameter.setConnectionPrice(Double.parseDouble(getElementTextContent(elementParameter, "getOperatorPrice")));

            return tariff;
        }

        private String getElementTextContent(Element parent, String childName) {
            NodeList nList = parent.getElementsByTagName(childName);
            Node node = nList.item(0);
            String text = node.getTextContent();
            return text;
        }


}


