/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: BrokerTransformer.java,v 1.2 2004/05/26 00:07:01 inder Exp $ */

package com.sun.j2ee.blueprints.opc.webservicebroker.provider;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

public class BrokerTransformer {
    
	private static final Logger logger = LoggerFactory.getLogger(BrokerTransformer.class);
	
    public static final String ACTIVITY_INVOICE = "http://java.sun.com/blueprints/schemas/invoice-activity.xsd";
    public static final String AIRLINE_INVOICE = "http://java.sun.com/blueprints/schemas/invoice-airline.xsd";
    public static final String LODGING_INVOICE = "http://java.sun.com/blueprints/schemas/invoice-lodging.xsd";
    public static final String XSL_CLASSPATH_BASE = "/com/sun/j2ee/blueprints/opc/webservicebroker/provider/";
    
    public static final String ACTIVITY_XSL = "invoice-activity.xsl";    
    public static final String AIRLINE_XSL = "invoice-airline.xsl";
    public static final String LODGING_XSL = "invoice-lodging.xsl";

    
    private String transformedDoc = null;
    private ByteArrayOutputStream bos = null;
    private CharArrayWriter  caw = null;
    private SAXParser parser = null;
    private Map transformers = null;
    private TransformerFactory transformerFactory = null;
    
    /**
     *  The InputSource provided to this class needs to be read
     *  2 times. We need to read the source to determine what document
     *  transformation needs to be perfomed (e.g. which style sheet
     *  to apply) and then we need to transform the actuall document.
     *  In order to do this we coppy the InputSource to a ByteArrayOutputStream
     *  so that we can create a new InputSource whenever we need to do something 
     *  with the doucment.
     */
    
    public BrokerTransformer() {
        SAXParserFactory sparserFactory = SAXParserFactory.newInstance();
         try {
            sparserFactory.setValidating(true);
            sparserFactory.setNamespaceAware(true);
            parser = sparserFactory.newSAXParser();
            // create Transfromers
            addTransformer(ACTIVITY_XSL);
            addTransformer(AIRLINE_XSL);
            addTransformer(LODGING_XSL);
        } catch (Exception ex) {
           logger.error("BrokerTransformer initizalization error: ", ex);
        }
    }
    
    private void addTransformer(String name) {
        if (transformers == null) transformers = new HashMap();
        if (transformerFactory == null) {
               transformerFactory = TransformerFactory.newInstance();
        }
        StreamSource tranformerXSL = 
                    new StreamSource(getClass().getResourceAsStream(
                                   XSL_CLASSPATH_BASE + name));
        try {
            Transformer tempTransfomer =  
                   transformerFactory.newTransformer(tranformerXSL);
                    transformers.put(name, tempTransfomer);
        } catch (TransformerConfigurationException fcx) {
        	logger.error(fcx.getMessage(), fcx);
        }

    }
    
    private void  init(InputSource in) {
        try { 
            transformedDoc = null;
            caw = new CharArrayWriter(); 
            Reader reader = in.getCharacterStream();
            // copy InputSource to CharArrayWriter the  so we can re-create the InputSource
            caw = new CharArrayWriter();
            long total =0;
            char [] buff = new char[1024];
            while (true) {
                int read = reader.read(buff,0,buff.length);
                total += read;
                if (read <=0) break;
                caw.write(buff,0, read);
            }
            caw.close();
            reader.close();
        } catch (java.io.IOException iox) {
            logger.error(iox.getMessage(), iox);
        }
    }
    
    public String transform(InputSource is) {
        // Make a local copy of the InputSource so we can use it twice
        init(is);
        try {
            // find out the document type and based on that apply the correct 
            // transformation
            parser.parse(new InputSource(new CharArrayReader(caw.toCharArray())),
                        new DefaultHandler() {
                                 private boolean foundFirst = false;
                                public void startElement(String namespace,
                                                              String name,
                                                              String qName,
                                                              Attributes attrs) {
                                   if (!foundFirst) {
                                      if ( name.equals("Invoice") ) {
                                        String schemaLocation = attrs.getValue("xsi:schemaLocation");
                                        if (schemaLocation.endsWith( AIRLINE_INVOICE)) {
                                            doXSLTTransformation((Transformer)transformers.get(AIRLINE_XSL));
                                        } else if (schemaLocation.endsWith( ACTIVITY_INVOICE)) {
                                            doXSLTTransformation((Transformer)transformers.get(ACTIVITY_XSL));
                                        } else if (schemaLocation.endsWith( LODGING_INVOICE)) {
                                            doXSLTTransformation((Transformer)transformers.get(LODGING_XSL));
                                        }
                                }
                         }
                    }
            });
        } catch (org.xml.sax.SAXException ex) {
            logger.error("BrokerTransformer error: ", ex);
        } catch (IOException iox) {
            logger.error("BrokerTransformer error: ", iox);
        }
        return transformedDoc;
    }
    
    private void doXSLTTransformation (Transformer transformer) {
        StreamSource in = new StreamSource(new CharArrayReader(caw.toCharArray()));
        StreamResult result = new StreamResult(new ByteArrayOutputStream());
          try {    
            if (transformer != null) {
                transformer.transform(in,result);
                String enc = transformer.getOutputProperty(OutputKeys.ENCODING);
                 transformedDoc =
                     ((ByteArrayOutputStream)result.getOutputStream()).toString(enc);
            } else {
                logger.error("BrokerTransformer error: Transformer not set");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
