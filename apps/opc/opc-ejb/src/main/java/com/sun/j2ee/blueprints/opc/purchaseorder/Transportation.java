/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Transportation.java,v 1.2 2004/05/26 00:06:54 inder Exp $ */

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

public class Transportation implements Serializable{

  protected String transportationId;
  protected String carrier;
  protected String origin;
  protected String destination;
  protected Calendar departureDate;
  protected String departureTime;
  protected float price;
  protected String travelClass;
  protected int headCount;

  // Constructor
  public Transportation() {}

  public Transportation(String transportationId, String carrier, String origin,
                        String destination, Calendar departureDate,
                        String departureTime, float price, String travelClass, int headCount) {
    this.transportationId = transportationId;
    this.carrier = carrier;
    this.origin = origin;
    this.destination = destination;
    this.departureDate = departureDate;
    this.price = price;
    this.travelClass = travelClass;
    this.departureTime = departureTime;
    this.headCount = headCount;
  }

  // getter methods
  public String getTransportationId() {
    return transportationId;
  }

  public String getCarrier() {
    return carrier;
  }

  public String getOrigin() {
    return origin;
  }

  public String getDestination() {
    return destination;
  }

  public Calendar getDepartureDate() {
    return departureDate;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public float getPrice() {
    return price;
  }

  public String getTravelClass() {
    return travelClass;
  }
  public int getHeadCount() {
    return headCount;
  }

  // setter methods
  public void setTransportationId(String transportationId) {
    this.transportationId = transportationId;
  }

  public void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public void setDepartureDate(Calendar departureDate) {
    this.departureDate = departureDate;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setTravelClass(String travelClass) {
    this.travelClass = travelClass;
  }

  public void setHeadCount(int headCount) {
    this.headCount = headCount;
  }

  //XML serialization methods
  public String toXML(String poId) throws XMLException{
          
      String transportPO = null;      
      try{
          
          //construct the DOM tree
          DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
          docBuilderFactory.setNamespaceAware(true);
          DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();          
          Element  transportElem = doc.createElement("Transportation");
          doc.appendChild(transportElem);          
          Element  elem = doc.createElement("OPCPoId");          
          elem.appendChild(doc.createTextNode(poId));
          transportElem.appendChild(elem);          
          elem = doc.createElement("TransportationId");          
          elem.appendChild(doc.createTextNode(transportationId));
          transportElem.appendChild(elem);          
          elem = doc.createElement("DepartureDate");
          elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(departureDate.getTime())));
          transportElem.appendChild(elem);          
          elem = doc.createElement("HeadCount");
          elem.appendChild(doc.createTextNode(Integer.toString(headCount)));
          transportElem.appendChild(elem);               
         
          //process the source tree
          ByteArrayOutputStream baStream = new ByteArrayOutputStream();
          Result res = new StreamResult(baStream);
          TransformerFactory transFactory = TransformerFactory.newInstance();
          Transformer transformer = transFactory.newTransformer();
          transformer.setOutputProperty(OutputKeys.METHOD, "xml");          
          transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(new DOMSource(doc), res);
          transportPO = baStream.toString("UTF-8");  
          
      } catch(Exception exe){
          throw new XMLException(exe);         
      }
      return transportPO;   
  }  
}
