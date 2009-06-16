package com.sun.j2ee.blueprints.opc.webservicebroker.provider;

 import org.xml.sax.*;
 import java.io.*;


 public class BrokerEntityResolver implements EntityResolver {
     
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
       InputStream is =  null;
       try {
           is  = getClass().getResourceAsStream(PACKAGE_LOCATION + name);
           return new InputSource(is);
       } catch (Exception e) {
           System.err.println("BrokerEntityResolver error resolving: " + name);
           e.printStackTrace();
       }
       // default to the default entity resolver
       return null;
   }
 }
 
