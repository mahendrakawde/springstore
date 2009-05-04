/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: OrderFillerBean.java,v 1.4 2004/05/26 00:06:50 inder Exp $ */

package com.sun.j2ee.blueprints.opc.orderfiller;

import java.text.*;
import java.io.*;
import java.util.*;
import javax.ejb.*;
import javax.jms.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;
import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.opc.utils.*;

/**
 * This component splits a PO and sends string POs
 * to the Web service broker queue
 */
public class OrderFillerBean implements MessageDrivenBean, MessageListener {

    private  MessageDrivenContext context;
    private PurchaseOrder po;
 
    public void setMessageDrivenContext(MessageDrivenContext context) {
        this.context=context;
    }  
      
    public void ejbCreate() {}
      
    public void onMessage(Message message) {        
        try {
            String docType = message.getStringProperty(JNDINames.DOC_TYPE);

            //send the PO to the broker queue 
            if(message instanceof ObjectMessage){
                ObjectMessage objMsg = (ObjectMessage) message;
                po = (PurchaseOrder)objMsg.getObject();
            }    
            if(po != null){          
                sendPO(po);
            }
               
        } catch (Exception exe) {
            System.err.println(exe);
            throw new EJBException(exe);
        }                    
    }
    
    public void ejbRemove(){}

    private void sendPO(PurchaseOrder po) throws XMLException{
  
        //get the POs in xml String format
        Lodging lodging = po.getLodging();
        Transportation depFlight = po.getDepartureFlightInfo();
        Transportation retFlight = po.getReturnFlightInfo();
        Activity[] activities = po.getActivities();
        if(lodging != null)
            JMSUtils.sendMessage(JNDINames.WS_BROKER_MDB_QUEUE, 
                           JNDINames.DOC_TYPE, 
         JNDINames.LODGING_ORDER, 
         lodging.toXML(po.getPoId()));
        if((depFlight != null) || (retFlight != null))
            JMSUtils.sendMessage(JNDINames.WS_BROKER_MDB_QUEUE, 
         JNDINames.DOC_TYPE,
         JNDINames.AIRLINE_ORDER, 
         getTransportationPO(depFlight,
                                 retFlight, po.getPoId()));
        if((po.getActivities().length)!= 0)      
            JMSUtils.sendMessage(JNDINames.WS_BROKER_MDB_QUEUE, 
         JNDINames.DOC_TYPE,
         JNDINames.ACTIVITY_ORDER, 
         getActivityPO(activities, po.getPoId()));
       
    }

  private String getActivityPO(Activity[] acts, String poId) throws XMLException{
      
      //get an activity PO combining all the activities       
      String actyPO = null;      
      try{
          
          //construct the DOM tree
          DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
          docBuilderFactory.setNamespaceAware(true);
          DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();          
          Element  actsElem = doc.createElement("Activities");
          doc.appendChild(actsElem);          
          Element  elem = doc.createElement("OPCPoId");          
          elem.appendChild(doc.createTextNode(poId));
          actsElem.appendChild(elem);

          //add all the activities
          for (int i = 0; i < acts.length; ++i){
              Element actyElem = doc.createElement("Activity");          
              actsElem.appendChild(actyElem);             
              elem = doc.createElement("ActivityId");          
              elem.appendChild(doc.createTextNode(acts[i].getActivityId()));
              actyElem.appendChild(elem);          
              elem = doc.createElement("StartDate");
              elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(acts[i].getStartDate().getTime())));
              actyElem.appendChild(elem);          
              elem = doc.createElement("EndDate");
              elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(acts[i].getEndDate().getTime())));
              actyElem.appendChild(elem);          
              elem = doc.createElement("HeadCount");        
              elem.appendChild(doc.createTextNode(Integer.toString(acts[i].getHeadCount())));
              actyElem.appendChild(elem);
          }          
                   
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

  private String getTransportationPO(Transportation dep, Transportation ret, String poId) throws XMLException {
      
      //get a transportation PO
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
          elem = doc.createElement("DepartureTransportationId");
          //This is a temporary fix. This is done since the schema for the
          //supplier PO expects the DepartureTransportationId & DepartureDate
          //to be always included. The schema has to be modified to properly
          //validate POs without DepartureTransportationId  & DepartureDate                
          if(dep == null){
              elem.appendChild(doc.createTextNode("null"));
          } else {
              elem.appendChild(doc.createTextNode(dep.getTransportationId()));
          }
          transportElem.appendChild(elem);          
          elem = doc.createElement("DepartureDate");
          if(dep == null){
              elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(Calendar.getInstance().getTime())));
          } else {                 
              elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(dep.getDepartureDate().getTime())));
          }
          transportElem.appendChild(elem);
          elem = doc.createElement("ReturnTransportationId");  
          //This is a temporary fix. This is done since the schema for the
          //supplier PO expects the ReturnTransportationId & ReturnDate
          //to be always included. The schema has to be modified to properly
          //validate POs without ReturnTransportationId & ReturnDate                       
          if(ret == null){ 
              elem.appendChild(doc.createTextNode("null"));
          } else {
              elem.appendChild(doc.createTextNode(ret.getTransportationId()));
          }
          transportElem.appendChild(elem);          
          elem = doc.createElement("ReturnDate");
          if(ret == null){
              elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(Calendar.getInstance().getTime())));
          } else {                 
              elem.appendChild(doc.createTextNode((new SimpleDateFormat("MM-dd-yy")).format(ret.getDepartureDate().getTime())));
          }
          transportElem.appendChild(elem);
          String headCount = dep != null ? Integer.toString(dep.getHeadCount()): Integer.toString(ret.getHeadCount()) ;        
          elem = doc.createElement("HeadCount");
          elem.appendChild(doc.createTextNode(headCount));
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

