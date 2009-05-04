/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Lodging.java,v 1.2 2004/05/26 00:06:54 inder Exp $ */

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

public class Lodging implements Serializable{

  protected String lodgingId;
  protected String name;
  protected float pricePerNight;
  protected String location;
  protected Calendar startDate;
  protected Calendar endDate;
  protected int noNights;
  protected int noRooms;

  // Constructor
  public Lodging() {}

  public Lodging(String lodgingId, String name, float pricePerNight,
                 String location, Calendar startDate, Calendar endDate, int noNights,
                 int noRooms) {
      this.lodgingId = lodgingId;
      this.name = name;
      this.pricePerNight = pricePerNight;
      this.location = location;
      this.startDate = startDate;
      this.endDate = endDate;
      this.noNights = noNights;
      this.noRooms = noRooms;

  }

  // getter methods
  public String getLodgingId() {
      return lodgingId;
  }

  public String getName() {
      return name;
  }

  public float getPricePerNight() {
      return pricePerNight;
  }

  public String getLocation() {
      return location;
  }

  public Calendar getStartDate() {
      return startDate;
  }

  public Calendar getEndDate() {
      return endDate;
  }

  public int getNoNights() {
      return noNights;
  }

  public int getNoRooms() {
      return noRooms;
  }

  // setter methods
  public void setLodgingId(String lodgingId) {
      this.lodgingId = lodgingId;
  }

  public void setName(String name) {
      this.name = name;
  }

  public void setPricePerNight(float pricePerNight) {
      this.pricePerNight = pricePerNight;
  }

  public void setLocation(String location) {
      this.location = location;
  }

  public void setStartDate(Calendar startDate) {
      this.startDate = startDate;
  }

  public void setEndDate(Calendar endDate) {
      this.endDate = endDate;
  }

  public void setNoNights(int noNights) {
      this.noNights = noNights;
  }

  public void setNoRooms(int noRooms) {
      this.noRooms = noRooms;
  }
  
  //XML serialization methods
  public String toXML(String poId) throws XMLException{
          
      String lodgingPO = null;      
      try{
          
          //construct the DOM tree
          DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
          docBuilderFactory.setNamespaceAware(true);
          DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();          
          Element  lodgingElem = doc.createElement("Lodging");
          doc.appendChild(lodgingElem);          
          Element  elem = doc.createElement("OPCPoId");          
          elem.appendChild(doc.createTextNode(poId));
          lodgingElem.appendChild(elem);          
          elem = doc.createElement("LodgingId");          
          elem.appendChild(doc.createTextNode(lodgingId));
          lodgingElem.appendChild(elem);          
          elem = doc.createElement("StartDate");
          elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(startDate.getTime())));
          lodgingElem.appendChild(elem);          
          elem = doc.createElement("EndDate");
          elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(endDate.getTime())));
          lodgingElem.appendChild(elem);          
          elem = doc.createElement("Nights");        
          elem.appendChild(doc.createTextNode(Integer.toString(noNights)));
          lodgingElem.appendChild(elem);          
          elem = doc.createElement("Rooms");        
          elem.appendChild(doc.createTextNode(Integer.toString(noRooms)));
          lodgingElem.appendChild(elem);    
         
          //process the source tree
          ByteArrayOutputStream baStream = new ByteArrayOutputStream();
          Result res = new StreamResult(baStream);
          TransformerFactory transFactory = TransformerFactory.newInstance();
          Transformer transformer = transFactory.newTransformer();
          transformer.setOutputProperty(OutputKeys.METHOD, "xml");          
          transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(new DOMSource(doc), res);
          lodgingPO = baStream.toString("UTF-8");  
          
      } catch(Exception exe){
          throw new XMLException(exe);         
      }
      return lodgingPO;   
  } 

}
