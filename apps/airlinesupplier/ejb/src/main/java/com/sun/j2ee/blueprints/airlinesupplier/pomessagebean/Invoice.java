/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Invoice.java,v 1.7 2004/05/26 00:06:05 inder Exp $ */

package com.sun.j2ee.blueprints.airlinesupplier.pomessagebean;

import java.util.*;
import java.text.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class Invoice implements Serializable {

    protected String invoiceId;
    protected String opcPoId;
    protected String supplierId;
    protected String agentId;
    protected Calendar depDate;
    protected Calendar retDate;
    protected String depFlightId;
    protected String retFlightId;
    protected int numPassengers;
    protected String status;
    protected String cancelPolicy;
  
    // Constructor
    public Invoice() {}

    public Invoice(String invoiceId, String opcPoId, String supplier,
       String agentId, Calendar depDate, Calendar retDate,
       String depFlightId, String retFlightId, int numPassengers,  
       String status, String cancelPolicy) {
  this.invoiceId = invoiceId;
  this.opcPoId = opcPoId;
  this.supplierId = supplier;
        this.agentId = agentId;
        this.depDate = depDate;
        this.retDate = retDate;
        this.depFlightId = depFlightId;
        this.retFlightId = retFlightId;
        this.numPassengers = numPassengers;
  this.status = status;
        this.cancelPolicy = cancelPolicy;
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
                                 UnsupportedEncodingException {
  String inv = null;      

  //construct the DOM tree
  SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd E HH:mm.ss a");
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
        "http://java.sun.com/blueprints/schemas/invoice-airline.xsd");
  invElem.setAttribute("xmlns", 
           "http://java.sun.com/blueprints/ns/invoice");
  doc.appendChild(invElem);
  Element elem = doc.createElement("InvoiceRef");
  elem.appendChild(doc.createTextNode(invoiceId));
  invElem.appendChild(elem);
  elem = doc.createElement("OPCPoId");          
  elem.appendChild(doc.createTextNode(opcPoId));
  invElem.appendChild(elem);
  elem = doc.createElement("SupplierId");          
  elem.appendChild(doc.createTextNode(supplierId));
  invElem.appendChild(elem);
  elem = doc.createElement("AgentId");          
  elem.appendChild(doc.createTextNode(agentId));
  invElem.appendChild(elem);
  elem = doc.createElement("DPT-Flight-Date");          
  elem.appendChild(doc.createTextNode(df.format(depDate.getTime())));
  invElem.appendChild(elem);
  elem = doc.createElement("RTN-Flight-Date");          
  elem.appendChild(doc.createTextNode(df.format(retDate.getTime())));
  invElem.appendChild(elem);
  elem = doc.createElement("DPT-Flight-ID");          
  elem.appendChild(doc.createTextNode(depFlightId));
  invElem.appendChild(elem);
  elem = doc.createElement("RTN-Flight-ID");          
  elem.appendChild(doc.createTextNode(retFlightId));
  invElem.appendChild(elem);
  elem = doc.createElement("NUM-Passengers");          
  elem.appendChild(doc.createTextNode(numPassengers + ""));
  invElem.appendChild(elem);
  elem = doc.createElement("Status");          
  elem.appendChild(doc.createTextNode(status));
  invElem.appendChild(elem);
  elem = doc.createElement("Cancel-Policy");          
  elem.appendChild(doc.createTextNode(cancelPolicy));
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
