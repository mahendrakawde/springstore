/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AirlineOrder.java,v 1.4 2004/07/19 22:46:28 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.airlinesupplier.powebservice;

import java.util.*;
import java.text.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class AirlineOrder implements Serializable {

    protected String orderId;
    protected String depFlightId;
    protected Calendar depFlightDate;
    protected String retFlightId;
    protected Calendar retFlightDate;
    protected int headCount;

    // Constructor
    public AirlineOrder() {}

    public AirlineOrder(String orderId, String depFlightId, 
      Calendar depFlightDate, String retFlightId,
      Calendar retFlightDate, int headCount) {
        this.orderId = orderId;
        this.depFlightId = depFlightId;
        this.depFlightDate = depFlightDate;
        this.retFlightId = retFlightId;
        this.retFlightDate = retFlightDate;
        this.headCount = headCount;
    }

    // getter methods
    public String getOrderId() {
        return orderId;
    }

    public String getDepFlightId() {
        return depFlightId;
    }

    public Calendar getDepFlightDate() {
        return depFlightDate;
    }

    public String getRetFlightId() {
        return retFlightId;
    }

    public Calendar getRetFlightDate() {
        return retFlightDate;
    }

    public int getHeadCount() {
        return headCount;
    }

    // setter methods
    public void setOrderId(String id) {
        this.orderId = id;
    }

    public void setDepFlightId(String id) {
        this.depFlightId = id;
    }

    public void setDepFlightDate(Calendar depFlightDate) {
        this.depFlightDate = depFlightDate;
    }

    public void setRetFlightId(String id) {
        this.retFlightId = id;
    }

    public void setRetFlightDate(Calendar retFlightDate) {
        this.retFlightDate = retFlightDate;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    public static AirlineOrder fromXML(String flightPO)
        throws InvalidOrderException{
      
        AirlineOrder flight = null;      
        try { 
          
            InputSource source = new InputSource(new StringReader(flightPO));      
            DocumentBuilderFactory docBuilderFactory =
    DocumentBuilderFactory.newInstance();
            docBuilderFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();      
            //parse the source doc and extract details
            Document doc = docBuilder.parse(source);      
            Element elem = (Element)doc.getDocumentElement().getFirstChild().getNextSibling();
            flight = new AirlineOrder();

      // OrderId
            flight.setOrderId(((Text)(elem.getFirstChild())).getData());

      // DepFlightId
            elem =  getNextSibling(elem);
            flight.setDepFlightId(((Text)(elem.getFirstChild())).getData());

      // DepFlightDate
            elem = getNextSibling(elem); 
            Date date = new SimpleDateFormat("MM-dd-yy").parse(((Text)(elem.getFirstChild())).getData());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            flight.setDepFlightDate(cal);

      // RetFlightId
            elem =  getNextSibling(elem);
            flight.setRetFlightId(((Text)(elem.getFirstChild())).getData());

      // RetFlightDate
            elem = getNextSibling(elem); 
            date = new SimpleDateFormat("MM-dd-yy").parse(((Text)(elem.getFirstChild())).getData());
            cal = Calendar.getInstance();
            cal.setTime(date);
            flight.setRetFlightDate(cal);

      // HeadCount
            elem =  getNextSibling(elem);
            flight.setHeadCount(Integer.parseInt(((Text)(elem.getFirstChild())).getData()));
        } catch(Exception exe){
            exe.printStackTrace(System.err);
            throw new InvalidOrderException("PO for Airline not valid : " +
                                            exe.getMessage());
        }      
        return flight;   
    }
    
    public static Element getNextSibling(Element elem) {
        for(Node sib=elem.getNextSibling(); sib!=null; sib=sib.getNextSibling()){
            if(sib.getNodeType() == Node.ELEMENT_NODE){
                return (Element) sib;
            }            
        }
        return null;     
    }
}

