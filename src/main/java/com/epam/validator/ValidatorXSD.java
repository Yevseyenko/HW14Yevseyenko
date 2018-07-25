package com.epam.validator;

import com.epam.constants.Constants;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import java.io.IOException;

public class ValidatorXSD {
    public final static Logger log = Logger.getLogger(ValidatorXSD.class);
    public static void main (String[] args){
    String filename = Constants.FILENAME_XML;
    DOMParser parser = new DOMParser();
           try {
            parser.setErrorHandler(new MyErrorHandler());
            //settings validation with XSD
               parser.setFeature("http://xml.org/sax/features/validation", true);
        parser.setFeature("http://apache.org/xml/features/validation/schema", true);
        parser.parse(filename);
           } catch (IOException e) {
            e.printStackTrace();
            log.info("I/O error");
        } catch (SAXNotSupportedException e) {
               e.printStackTrace();
               log.info("Not supported operation");
           } catch (SAXNotRecognizedException e) {
               e.printStackTrace();
               log.info("Identificator not recognized");
           } catch (SAXException e) {
               e.printStackTrace();
               log.info("Global Sax error");
           }
log.info("Validation "+filename+" completed");
    }}
