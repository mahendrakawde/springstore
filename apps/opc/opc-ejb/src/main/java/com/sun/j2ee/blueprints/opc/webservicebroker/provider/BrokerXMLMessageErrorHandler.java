package com.sun.j2ee.blueprints.opc.webservicebroker.provider;

import org.xml.sax.*;

public class BrokerXMLMessageErrorHandler implements ErrorHandler {
    
           public void warning(SAXParseException ex) throws SAXException {
                        return;
          }

          public void error(SAXParseException ex) throws SAXException {
                        throw ex;
          }

          public void fatalError(SAXParseException ex) throws SAXException {
                        throw ex;
          }
}
