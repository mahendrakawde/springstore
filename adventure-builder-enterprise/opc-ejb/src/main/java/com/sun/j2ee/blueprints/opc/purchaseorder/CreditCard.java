/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CreditCard.java,v 1.2 2004/05/26 00:06:54 inder Exp $ */

package com.sun.j2ee.blueprints.opc.purchaseorder;

import java.util.*;
import java.text.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class CreditCard implements Serializable{
  protected String cardNumber;
  protected String cardExpiryDate;
  protected String cardType;

  public CreditCard() {}

  public CreditCard(String cardNumber, String cardExpiryDate, String cardType) {
    this.cardNumber = cardNumber;
    this.cardExpiryDate = cardExpiryDate;
    this.cardType = cardType;
    return;
  }

  // getter methods

  public String getCardNumber() {
    return cardNumber;
  }

  public String getCardExpiryDate() {
    return cardExpiryDate;
  }

  public String getCardType() {
    return cardType;
  }

  // setter methods

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public void setCardExpiryDate(String cardExpiryDate) {
    this.cardExpiryDate = cardExpiryDate;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  //XML serialization methods
  public String toXML() throws XMLException{
          
      String credCard = null;      
      try{
          
          //construct the DOM tree
          DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
          docBuilderFactory.setNamespaceAware(true);
          DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();          
          Element  ccElem = doc.createElement("CreditCard");
          doc.appendChild(ccElem);          
          Element  elem = doc.createElement("CardNumber");          
          elem.appendChild(doc.createTextNode(cardNumber));
          ccElem.appendChild(elem);          
          elem = doc.createElement("CardExpiryDate");          
          elem.appendChild(doc.createTextNode(cardExpiryDate));
          ccElem.appendChild(elem);          
          elem = doc.createElement("CardType");        
          elem.appendChild(doc.createTextNode(cardType));
          ccElem.appendChild(elem);          
                   
          //process the source tree
          ByteArrayOutputStream baStream = new ByteArrayOutputStream();
          Result res = new StreamResult(baStream);
          TransformerFactory transFactory = TransformerFactory.newInstance();
          Transformer transformer = transFactory.newTransformer();
          transformer.setOutputProperty(OutputKeys.METHOD, "xml");          
          transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(new DOMSource(doc), res);
          credCard = baStream.toString("UTF-8");  
          
      } catch(Exception exe){
          throw new XMLException(exe);         
      }
      return credCard;   
  }

}
