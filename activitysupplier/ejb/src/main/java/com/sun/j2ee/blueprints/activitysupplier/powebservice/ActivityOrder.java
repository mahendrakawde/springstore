/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityOrder.java,v 1.6 2004/05/26 00:05:58 inder Exp $ */

package com.sun.j2ee.blueprints.activitysupplier.powebservice;

import java.util.*;
import java.text.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class ActivityOrder implements Serializable {

    protected String orderId;
    protected ArrayList activities;

    // Constructor
    public ActivityOrder() {}

    // getter methods
    public String getOrderId() {
  return orderId;
    }

    public ArrayList getActivities() {
  return activities;
    }

    // setter methods
    public void setOrderId(String id) {
  this.orderId = id;
    }

    public void setActivities(ArrayList acts) {
  this.activities = acts;
    }

    public static ActivityOrder fromXML(String actyPO) throws 
                                          InvalidOrderException{
      
  ActivityOrder order = null;
  String opcPoId = null;
  ArrayList acts = new ArrayList();
  try { 
      
      InputSource source = new InputSource(new StringReader(actyPO));
      DocumentBuilderFactory docBuilderFactory = 
                       DocumentBuilderFactory.newInstance();
      docBuilderFactory.setNamespaceAware(true);
      DocumentBuilder docBuilder = 
                       docBuilderFactory.newDocumentBuilder();
      
      //parse the source doc and extract details
      Document doc = docBuilder.parse(source);      
      Element elem = 
      (Element)doc.getDocumentElement().getFirstChild().getNextSibling();
      opcPoId = ((Text)(elem.getFirstChild())).getData();
      for (elem = getNextSibling(elem); elem != null; 
                            elem = getNextSibling(elem)) {
          ActivityDetails acty = new ActivityDetails();
          Element actyelem = (Element)elem.getFirstChild().getNextSibling(); 
    acty.setActivityId(((Text)(actyelem.getFirstChild())).getData());
          actyelem = getNextSibling(actyelem);
          Date date = new SimpleDateFormat("MM-dd-yy").parse(((Text)(actyelem.getFirstChild())).getData());
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          acty.setStartDate(cal);
          actyelem =  getNextSibling(actyelem); 
    date = new SimpleDateFormat("MM-dd-yy").parse(((Text)(actyelem.getFirstChild())).getData());
          cal = Calendar.getInstance();
          cal.setTime(date);
          acty.setEndDate(cal);
          actyelem =  getNextSibling(actyelem);
            acty.setHeadCount(Integer.parseInt(((Text)(actyelem.getFirstChild())).getData()));
    acts.add(acty);
      }
  } catch(Exception exe){
      exe.printStackTrace(System.err);
      throw new InvalidOrderException("PO for Activity not valid : " +
              exe.getMessage());
  }      
  if(acts.size() != 0) {
      order = new ActivityOrder();
      order.setOrderId(opcPoId);
      order.setActivities(acts);
  }
  return order;
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
