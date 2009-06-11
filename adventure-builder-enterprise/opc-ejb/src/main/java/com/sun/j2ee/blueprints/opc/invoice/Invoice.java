/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Invoice.java,v 1.4 2004/05/26 00:06:48 inder Exp $ */

package com.sun.j2ee.blueprints.opc.invoice;

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

    // Constructor
    public Invoice() {}

    public Invoice(String invoiceId, String opcPoId, String supplier,
       String status) {
  this.invoiceId = invoiceId;
  this.opcPoId = opcPoId;
  this.supplierId = supplier;
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

    //XML deserialization methods
  
    public static Invoice fromXML(String invDoc) throws XMLException {
  Invoice invObj = null;
  try { 
          
      InputSource source = new InputSource(new StringReader(invDoc));
      DocumentBuilderFactory docBuilderFactory = 
    DocumentBuilderFactory.newInstance();
      docBuilderFactory.setNamespaceAware(true);
      DocumentBuilder docBuilder = 
    docBuilderFactory.newDocumentBuilder();

      //parse the source doc and extract details
      Document doc = docBuilder.parse(source);   
      Element elem = (Element)doc.getDocumentElement().getFirstChild();
      invObj = new Invoice();
      invObj.setInvoiceId(((Text)(elem.getFirstChild())).getData());
      elem =  getNextSibling(elem);
      invObj.setOpcPoId(((Text)(elem.getFirstChild())).getData());
      elem =  getNextSibling(elem);
      invObj.setSupplierId(((Text)(elem.getFirstChild())).getData());
      elem = getNextSibling(elem); 
      invObj.setStatus(((Text)(elem.getFirstChild())).getData());
  } catch(Exception exe){
      throw new XMLException("Invoice XML Exception");
  }
  return invObj;   
    }

    public static Element getNextSibling(Element elem) {
  for(Node sib=elem.getNextSibling(); sib!=null; 
      sib=sib.getNextSibling()){
      if(sib.getNodeType() == Node.ELEMENT_NODE){
    return (Element) sib;
      }            
  }
  return null;     
    }
}
