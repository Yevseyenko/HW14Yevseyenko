package com.epam.parsers;

import com.epam.model.Tariff;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TariffStAXParcer {
    private static final Logger log = Logger.getLogger(TariffStAXParcer.class);

    public List<Tariff> parse(String xmlPath) throws IOException, XMLStreamException {
        log.info("Run parsing XML with StAXParser");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlPath));
        List<Tariff> tariffs = new ArrayList<>();
        Tariff tariff = null;
        StartElement startElement;
        String element;
        TariffTags tariffTags;
        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                startElement = xmlEvent.asStartElement();
                element = startElement.getName().getLocalPart();
                tariffTags = TariffTags.valueOf(element.toUpperCase());
                switch (tariffTags) {
                    case NAME:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setName(xmlEvent.asCharacters().getData());
                        break;
                    case OPERATORNAME:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setOperatorName(xmlEvent.asCharacters().getData());
                        break;
                    case HADFAVOURITENUMBER:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.parameters.setHadFavouriteNumber(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
                        break;
                    case PAYROLL:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setPayroll(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                    case PRICESMS:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setPriceSMS(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                    case INSIDECALLPRICE:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.callprices.setInsideCallPrice(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                    case OUTSIDECALLPRICE:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.callprices.setOutsideCallPrice(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                    case STATIONARYCALLPRICE:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.callprices.setStationaryCallPrice(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                    case TARIFFICATION:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.parameters.setTariffication(xmlEvent.asCharacters().getData());
                        break;
                    case CONNECTIONPRICE:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.parameters.setConnectionPrice(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                }
            }
            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().
                        equalsIgnoreCase(TariffTags.tariff.name())) {
                    tariffs.add(tariff);
                }
            }
        }

        log.info("Successfully parsing XML with StAXParser");
        return tariffs;
    }
}
