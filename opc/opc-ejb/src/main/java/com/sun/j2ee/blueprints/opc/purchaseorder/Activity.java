/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Activity.java,v 1.2 2004/05/26 00:06:53 inder Exp $ */

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

public class Activity implements Serializable{

  protected String activityId;
  protected String name;
  protected float price;
  protected String location;
  protected Calendar startDate;
  protected Calendar endDate;
  protected int headCount;

  // Constructor
  public Activity() {}

  public Activity(String activityId, String name, float price, String location,
                  Calendar startDate, Calendar endDate, int headCount) {
    this.activityId = activityId;
    this.name = name;
    this.price = price;
    this.location = location;
    this.startDate = startDate;
    this.endDate = endDate;
    this.headCount = headCount;
  }

  // getter methods
  public String getActivityId() {
    return activityId;
  }

  public String getName() {
    return name;
  }

  public float getPrice() {
    return price;
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

  public int getHeadCount() {
    return headCount;
  }

  // setter methods
  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(float price) {
    this.price = price;
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

  public void setHeadCount(int headCount) {
    this.headCount = headCount;
  }
  
  //XML serialization methods
  public String toXML(String poId) throws XMLException{
          
      String actyPO = null;      
      try{
          
          //construct the DOM tree
          DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
          docBuilderFactory.setNamespaceAware(true);
          DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();          
          Element  actyElem = doc.createElement("Activity");
          doc.appendChild(actyElem);          
          Element  elem = doc.createElement("OPCPoId");          
          elem.appendChild(doc.createTextNode(poId));
          actyElem.appendChild(elem);          
          elem = doc.createElement("ActivityId");          
          elem.appendChild(doc.createTextNode(activityId));
          actyElem.appendChild(elem);          
          elem = doc.createElement("StartDate");
          elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(startDate.getTime())));
          actyElem.appendChild(elem);          
          elem = doc.createElement("EndDate");
          elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(endDate.getTime())));
          actyElem.appendChild(elem);          
          elem = doc.createElement("HeadCount");        
          elem.appendChild(doc.createTextNode(Integer.toString(headCount)));
          actyElem.appendChild(elem);          
                   
          //process the source tree
          ByteArrayOutputStream baStream = new ByteArrayOutputStream();
          Result res = new StreamResult(baStream);
          TransformerFactory transFactory = TransformerFactory.newInstance();
          Transformer transformer = transFactory.newTransformer();
          transformer.setOutputProperty(OutputKeys.METHOD, "xml");          
          transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(new DOMSource(doc), res);
          actyPO = baStream.toString("UTF-8");  
          
      } catch(Exception exe){
          throw new XMLException(exe);         
      }
      return actyPO;   
  }
}
