package com.epam.validator;

import com.epam.parsers.TarifDomParcer;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.IOException;

public class MyErrorHandler implements ErrorHandler {
    public final static Logger log = Logger.getLogger(MyErrorHandler.class);
  public MyErrorHandler() throws IOException {

  }
    @Override
    public void warning(SAXParseException exception) throws SAXException {
log.warn(getLineAddress(exception) + "-"+exception.getMessage() );
         }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        log.error(getLineAddress(exception) + "-"+exception.getMessage() );
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        log.fatal(getLineAddress(exception) + "-"+exception.getMessage() );
    }
    private String getLineAddress (SAXParseException e){
      return e.getLineNumber()+" : " +e.getColumnNumber();
    }
}
