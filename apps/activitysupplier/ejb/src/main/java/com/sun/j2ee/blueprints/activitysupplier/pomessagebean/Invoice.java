/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Invoice.java,v 1.7 2004/05/26 00:05:57 inder Exp $ */

package com.sun.j2ee.blueprints.activitysupplier.pomessagebean;

import java.util.*;
import java.text.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import com.sun.j2ee.blueprints.activitysupplier.powebservice.*;

public class Invoice implements Serializable {

    protected String invoiceId;
    protected String opcPoId;
    protected String supplierId;
    protected ActivityOrder actOrder;
    protected String status;

    // Constructor
    public Invoice() {}

    public Invoice(String invoiceId,
                                String opcPoId,
                                String supplier,
                                ActivityOrder actOrder,
                                String status) {
  this.invoiceId = invoiceId;
  this.opcPoId = opcPoId;
  this.supplierId = supplier;
        this.actOrder = actOrder;
  this.status = status;
    }

    // getter methods
    public String getInvoiceId() {
  return invoiceId;
    }

    public String getOpcPoId() {
  return opcPoId;
    }

    public String getSupplierId() {
  return supplierId;
    }

    public String getStatus() {
  return status;
    }

    // setter methods
    public void setInvoiceId(String invoiceId) {
  this.invoiceId = invoiceId;
    }

    public void setOpcPoId(String id) {
  this.opcPoId = id;
    }

    public void setSupplierId(String id) {
  this.supplierId = id;
    }

    public void setStatus(String stat) {
  this.status = stat;
    }

    //XML serialization/deserialization methods
    public String toXML() throws ParserConfigurationException,
                                 TransformerConfigurationException,
                                 TransformerException,
                                 UnsupportedEncodingException{
  String inv = null;      

  //construct the DOM tree
  SimpleDateFormat df = 
      new SimpleDateFormat("yyyy/MM/dd E HH:mm.ss a");
  DocumentBuilderFactory docBuilderFactory = 
      DocumentBuilderFactory.newInstance();
  docBuilderFactory.setNamespaceAware(true);
  DocumentBuilder docBuilder = 
      docBuilderFactory.newDocumentBuilder();
  Document doc = docBuilder.newDocument();          
  Element  invElem = doc.createElement("Invoice");
  invElem.setAttribute("xmlns:xsi", 
           "http://www.w3.org/2001/XMLSchema-instance");
  invElem.setAttribute("xsi:schemaLocation",
    "http://java.sun.com/blueprints/ns/invoice " + 
    "http://java.sun.com/blueprints/schemas/invoice-activity.xsd");
  invElem.setAttribute("xmlns", 
         "http://java.sun.com/blueprints/ns/invoice");
  doc.appendChild(invElem);
  Element elem = doc.createElement("ID");
  elem.appendChild(doc.createTextNode(invoiceId));
  invElem.appendChild(elem);
  elem = doc.createElement("OPCPoId");          
  elem.appendChild(doc.createTextNode(opcPoId));
  invElem.appendChild(elem);
  elem = doc.createElement("SupplierId");          
  elem.appendChild(doc.createTextNode(supplierId));
  invElem.appendChild(elem);
  // create the activities
  Element acts = doc.createElement("activities");
            
  // go through the list of activities
  ArrayList actList = actOrder.getActivities();
  Iterator it = null;
  if (actList != null) it = actList.iterator();
  while ((it != null) && it.hasNext()) {
      ActivityDetails ad = (ActivityDetails)it.next();
      Element act = doc.createElement("activity");
      Element actElem = doc.createElement("ACT-ID");     
      actElem.appendChild(doc.createTextNode(ad.getActivityId()));
      act.appendChild(actElem);        
      actElem = doc.createElement("Start-Date");          
      actElem.appendChild(doc.createTextNode(df.format(ad.getStartDate().getTime())));
      act.appendChild(actElem);
      actElem = doc.createElement("End-Date");          
      actElem.appendChild(doc.createTextNode(df.format(ad.getEndDate().getTime())));
      act.appendChild(actElem);
      actElem = doc.createElement("Head-Count");          
      actElem.appendChild(doc.createTextNode(ad.getHeadCount() + ""));
      act.appendChild(actElem);
      acts.appendChild(act);
  }
  // add the activitiy to the activities
  invElem.appendChild(acts); 
            
  elem = doc.createElement("Status");          
  elem.appendChild(doc.createTextNode(status));
  invElem.appendChild(elem);
                   
  //process the source tree
  ByteArrayOutputStream baStream = new ByteArrayOutputStream();
  Result res = new StreamResult(baStream);
  TransformerFactory transFactory = TransformerFactory.newInstance();
  Transformer transformer = transFactory.newTransformer();
  transformer.setOutputProperty(OutputKeys.METHOD, "xml");          
  transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
  transformer.transform(new DOMSource(doc), res);
  inv = baStream.toString("UTF-8");  
  return inv;   
    }
}
