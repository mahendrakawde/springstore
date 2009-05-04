/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Invoice.java,v 1.9 2004/05/26 00:06:40 inder Exp $ */

package com.sun.j2ee.blueprints.lodgingsupplier.pomessagebean;

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
    protected String status;
    protected String hotelId;
    protected String hotelAddress;
    protected String cancelPolicy;

    // Constructor
    public Invoice() {}

    public Invoice(String invoiceId, String opcPoId, String supplier,
       String status, String hotelId, String hotelAddress,
       String cancelPolicy) {
  this.invoiceId = invoiceId;
  this.opcPoId = opcPoId;
  this.supplierId = supplier;
  this.status = status;
        this.hotelId = hotelId;
        this.hotelAddress = hotelAddress;
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
         "http://java.sun.com/blueprints/schemas/invoice-lodging.xsd");
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
  elem = doc.createElement("status");          
  elem.appendChild(doc.createTextNode(status));
  invElem.appendChild(elem);
  elem = doc.createElement("HotelId");          
  elem.appendChild(doc.createTextNode(hotelId));
  invElem.appendChild(elem);
  elem = doc.createElement("HotelAddress");          
  elem.appendChild(doc.createTextNode(hotelAddress));
  invElem.appendChild(elem);
  elem = doc.createElement("CancelPolicy");          
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
