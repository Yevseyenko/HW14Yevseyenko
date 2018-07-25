package com.epam.transformer;

import com.epam.parsers.TariffStAXParcer;
import org.apache.log4j.Logger;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformToHTML {
    private static final Logger log = Logger.getLogger(TransformToHTML.class);


    public void transformHTML(String xslFilePath, String xmlFilePath) {
        try {
            TransformerFactory tFact = TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = tFact.newTransformer(new StreamSource(xslFilePath));
            transformer.transform(new StreamSource(xmlFilePath), new StreamResult("tariff.html"));
            log.info("Transformation to HTML is successful.");
        } catch (TransformerException e) {
            log.info("T");
        }
    }
}