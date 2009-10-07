package com.sun.j2ee.blueprints.opc.webservicebroker.provider;

 import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;


 public class BrokerEntityResolver implements EntityResolver {
     
	 private static final Logger logger = LoggerFactory.getLogger(BrokerEntityResolver.class);
	 
     private static String PACKAGE_LOCATION = "/com/sun/j2ee/blueprints/opc/webservicebroker/provider/";
     private static String BLUEPRINTS_NS = "http://java.sun.com/blueprints/schemas/";
     
     public InputSource resolveEntity (String publicId, String systemId) {
        if (systemId.equals(BLUEPRINTS_NS + "invoice.xsd")) {
            return getClassPathSource("invoice.xsd");
        } else if (systemId.equals(BLUEPRINTS_NS + "invoice-activity.xsd")) {
            return getClassPathSource("invoice-activity.xsd");
        } else if (systemId.equals(BLUEPRINTS_NS + "invoice-airline.xsd")) {
            return getClassPathSource("invoice-airline.xsd");
        } else if (systemId.equals(BLUEPRINTS_NS + "invoice-lodging.xsd")) {
            return getClassPathSource("invoice-lodging.xsd");
        } else {
            // use the default behaviour
            return null;
        }
   }
   
   private InputSource getClassPathSource(String name) {
       try {
    	   InputStream is  = getClass().getResourceAsStream(PACKAGE_LOCATION + name);
           return new InputSource(is);
       } catch (Exception e) {
    	   logger.error("BrokerEntityResolver error resolving: {}", name);
    	   logger.error(e.getMessage(), e);
       }
       // default to the default entity resolver
       return null;
   }
 }
 
