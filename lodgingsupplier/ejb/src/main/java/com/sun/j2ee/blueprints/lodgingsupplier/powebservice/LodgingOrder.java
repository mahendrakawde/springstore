/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingOrder.java,v 1.3 2004/07/19 22:53:30 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.lodgingsupplier.powebservice;

import java.util.*;
import java.text.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class LodgingOrder implements Serializable {

    protected String orderId;
    protected String lodgingId;
    protected Calendar startDate;
    protected Calendar endDate;
    protected int headCount;

    // Constructor
    public LodgingOrder() {}

    public LodgingOrder(String orderId, String lodgingId, Calendar startDate,
                         Calendar endDate, int headCount) {
        this.orderId = orderId;
        this.lodgingId = lodgingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.headCount = headCount;
    }

    // getter methods
    public String getOrderId() {
        return orderId;
    }

    public String getLodgingId() {
        return lodgingId;
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
    public void setOrderId(String id) {
        this.orderId = id;
    }

    public void setLodgingId(String id) {
        this.lodgingId = id;
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

    public static LodgingOrder fromXML(String lodgyPO)
        throws InvalidOrderException{
      
        LodgingOrder lodgy = null;      
        try { 
          
            InputSource source = new InputSource(new StringReader(lodgyPO));      
            DocumentBuilderFactory docBuilderFactory =
    DocumentBuilderFactory.newInstance();
            docBuilderFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();      
            //parse the source doc and extract details
            Document doc = docBuilder.parse(source);      
            Element elem = (Element)doc.getDocumentElement().getFirstChild().getNextSibling();
            lodgy = new LodgingOrder();
            lodgy.setOrderId(((Text)(elem.getFirstChild())).getData());
            elem =  getNextSibling(elem);
            lodgy.setLodgingId(((Text)(elem.getFirstChild())).getData());
            elem = getNextSibling(elem); 
            Date date = new SimpleDateFormat("MM-dd-yy").parse(((Text)(elem.getFirstChild())).getData());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            lodgy.setStartDate(cal);
            elem =  getNextSibling(elem); 
            date = new SimpleDateFormat("MM-dd-yy").parse(((Text)(elem.getFirstChild())).getData());
            cal = Calendar.getInstance();
            cal.setTime(date);
            lodgy.setEndDate(cal);
            elem =  getNextSibling(elem);
            lodgy.setHeadCount(Integer.parseInt(((Text)(elem.getFirstChild())).getData()));
        } catch(Exception exe){
            exe.printStackTrace(System.err);
            throw new InvalidOrderException("PO for Lodging not valid : " +
                                            exe.getMessage());
        }      
        return lodgy;   
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

