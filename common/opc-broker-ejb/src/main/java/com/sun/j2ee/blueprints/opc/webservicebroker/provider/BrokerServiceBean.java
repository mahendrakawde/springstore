/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: BrokerServiceBean.java,v 1.6 2004/06/10 18:12:27 gmurray71 Exp $ */

package com.sun.j2ee.blueprints.opc.webservicebroker.provider;

import java.rmi.*;

import javax.ejb.*;

import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;
import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.opc.utils.*;
import com.sun.j2ee.blueprints.opc.serviceexceptions.*;

import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;

public class BrokerServiceBean implements SessionBean {

    private SessionContext sc;
    private BrokerTransformer bt = null;
 
    public BrokerServiceBean(){}
    
    public void ejbCreate() throws CreateException {
        bt = new BrokerTransformer();
    }

    public String submitDocument(String doc) throws InvalidDocumentException,
                                                    ProcessingException {
  // Step 1. Validate Doc
        
        boolean valid =  validate(doc);
        
        if (!valid) {
            throw new ProcessingException("BrokerServiceBean: document does not match schema" + doc);
        }
        // transform the doc
  String tDoc = bt.transform(new InputSource(new StringReader(doc)));
        if (tDoc != null) {
            doc = tDoc;
        }
  // Step 3. Map doc to domain obj
  // In this implementation, the Work Flow Manager expects the doc as it
  // is; no mapping required

  // Step 4. Find which Q to put the doc; 
  // In this implementation, the work flow
  // manager Q is the target for all messages; 

  // Step 5. Delegate the request to processing layer by sending it 
  // on to the Q
  if(delegateToProcessingLayer(JNDINames.WORKFLOW_MGR_MDB_QUEUE, doc)
                                                             == false) 
      throw new ProcessingException("Irrecoverable error while submitting requestfor processing");

  // Step 6. Send back a corrleation ID - sending a dummy value here
  return("INV1234");
    }

    private boolean delegateToProcessingLayer(String destQueue, String xmlDoc)
                                              throws ProcessingException {
  return(JMSUtils.sendMessage(destQueue, JNDINames.DOC_TYPE,
            JNDINames.INVOICE_DOCUMENT, xmlDoc));
    }

    public void setSessionContext(SessionContext sc) {
        this.sc = sc;
    }
    
    public void ejbRemove() throws RemoteException {
        bt = null;
    }
    
    //empty for Stateless EJBs
    public void ejbActivate() {}

    //empty for Stateless EJBs
    public void ejbPassivate() {}
    
    public boolean validate(String xmlText) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbf = null;
            DocumentBuilder db = null;
            try {
                dbf = DocumentBuilderFactory.newInstance();
                dbf.setValidating(true);
                dbf.setNamespaceAware(true);
                dbf.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", 
                                            "http://www.w3.org/2001/XMLSchema");
                if (dbf != null){
                    db = dbf.newDocumentBuilder();
                 }
                 db.setEntityResolver(new BrokerEntityResolver());
                 db.setErrorHandler(new BrokerXMLMessageErrorHandler());
            } catch ( javax.xml.parsers.ParserConfigurationException pce) {
                System.err.println(pce);
            }
          InputSource is =  new InputSource(new StringReader(xmlText));
            doc = db.parse(is);
            return true;
        } catch (Exception e) {
            System.err.println("BrokerServiceBean::getDocument error loading XML Document " + e);
        }
        return false;
    }
}
