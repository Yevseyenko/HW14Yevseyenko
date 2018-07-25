package com.epam.parsers;

import com.epam.model.Tariff;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TarifSAXParcer {
    private static final Logger log = Logger.getLogger(TarifSAXParcer.class);
    private List<Tariff> tariffs;
    Tariff tariff;
    TariffTags tariffTags;
    public List<Tariff> parse(String xmlPath) throws ParserConfigurationException, SAXException, IOException {
        log.info("Run with SAX parser");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        DefaultHandler defaultHandler = new DefaultHandler() {
            @Override
            public void startElement
                    (String uri, String localName, String qName, Attributes attributes) {
                if (!qName.equalsIgnoreCase(TariffTags.tariff.name())) {
                    tariffTags = TariffTags.valueOf(qName.toUpperCase());
                }
                if (tariffs == null) {
                    tariffs = new ArrayList<>();
                }
            }
            @Override
            public void endElement(String uri, String localName, String qName) {
                if (qName.equalsIgnoreCase(TariffTags.tariff.name())) {
                    tariffs.add(tariff);
                }
            }

            @Override
            public void characters(char ch[], int start, int length) {
                if (tariffTags != null) {
                    switch (tariffTags) {
                        case NAME:
                            tariff.setName(new String(ch, start, length));
                            break;
                        case OPERATORNAME:
                            tariff.setOperatorName(new String(ch, start, length));
                            break;
                        case HADFAVOURITENUMBER:
                            tariff.parameters.setHadFavouriteNumber(Boolean.parseBoolean(new String(ch, start, length)));
                            break;
                        case PAYROLL:
                            tariff.setPayroll(Double.parseDouble(new String(ch, start, length)));
                            break;
                        case PRICESMS:
                            tariff.setPriceSMS(Double.parseDouble(new String(ch, start, length)));
                            break;
                        case INSIDECALLPRICE:
                            tariff.callprices.setInsideCallPrice(Double.parseDouble(new String(ch, start, length)));
                            break;
                        case OUTSIDECALLPRICE:
                            tariff.callprices.setOutsideCallPrice(Double.parseDouble(new String(ch, start, length)));
                            break;
                        case STATIONARYCALLPRICE:
                            tariff.callprices.setStationaryCallPrice(Double.parseDouble(new String(ch, start, length)));
                            break;
                        case TARIFFICATION:
                            tariff.parameters.setTariffication(new String(ch, start, length));
                            break;
                        case CONNECTIONPRICE:
                            tariff.parameters.setConnectionPrice(Double.parseDouble(new String(ch, start, length)));
                            break;
                    }
                    tariffTags = null;
                }
            }
        };
        saxParser.parse(xmlPath, defaultHandler);
        log.info("Successfully parsing XML with SAXParser");
        return tariffs;
    }
}
