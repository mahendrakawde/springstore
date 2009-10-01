/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityPOEndpointBean.java,v 1.10 2004/07/30 23:59:19 inder Exp $ */

package com.sun.j2ee.blueprints.activitysupplier.powebservice;

import javax.ejb.*;
import java.rmi.*;
import javax.jms.*;
import com.sun.j2ee.blueprints.activitysupplier.JNDINames;
import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;

/**
 *  This class is the entry point for purchase orders submitted 
 *  by OPC to the activity suppliers;
 */
public class ActivityPOEndpointBean implements SessionBean {

    private SessionContext sc;
 
    public ActivityPOEndpointBean(){}
    
    public void ejbCreate() throws CreateException {}

    /**
     * Receive an order, preprocess the request, submit request to workflow
     * and return the order id so that the caller can have a correlation id
     * for the order
     */
    public String submitActivityReservationDetails(String xmlPO)
         throws InvalidOrderException, OrderSubmissionException,
                                                   RemoteException {

  // Do Interaction layer processing
  ActivityOrder order = preProcessInput(xmlPO);

  // Submit request thro JMS Queue
  submitRequest(order);

  // Return reference id - dummy ID for now
  return("ACT1234");
    }

    private ActivityOrder preProcessInput(String po)
                        throws InvalidOrderException {
  // XML doc should be ideally validated against its schema here;
  // Similar scenario already shown in OPC module;
  // Here it is skipped in this sample - we will convert doc to obj
  return(ActivityOrder.fromXML(po));
    }

    private void submitRequest(ActivityOrder act)
                        throws OrderSubmissionException {

  ConnectionFactory jmsConnectionFactory = null;
  Destination jmsDest = null;
  Connection jmsConnection = null;
  Session jmsSession = null;
  MessageProducer jmsSender = null;

  try {
      ServiceLocator sl = new ServiceLocator();
      jmsConnectionFactory = (ConnectionFactory)
      sl.getJMSConnectionFactory(JNDINames.ACT_QUEUECONNECTIONFACTORY);
      jmsDest = sl.getJMSDestination(JNDINames.ACT_QUEUE);
      jmsConnection = jmsConnectionFactory.createConnection();
      jmsSession = jmsConnection.createSession(true,
        Session.AUTO_ACKNOWLEDGE);
      jmsSender = jmsSession.createProducer(jmsDest);

      ObjectMessage message = jmsSession.createObjectMessage();
      message.setObject(act);
      jmsSender.send(message);
  } catch (ServiceLocatorException se) {
      throw new OrderSubmissionException("Error while sending a message:"
            + se.getMessage());
  } catch (JMSException e) {
      throw new OrderSubmissionException("Error while sending a message:"
            + e.getMessage());
  } finally {
      // close all JMS resources
      if (jmsSender != null) {
    try {
        jmsSender.close();
    } catch (JMSException e) {
        throw new OrderSubmissionException("Error sender close");
    }
      }
      if (jmsSession != null) {
    try {
        jmsSession.close();
    } catch (JMSException e) {
        throw new OrderSubmissionException("Error session close");
    }
      }
      if (jmsConnection != null) {
    try {
        jmsConnection.close();
    } catch (JMSException e) {
        throw new OrderSubmissionException("Error Connection close");
    }
      }
  }
    }

    public void setSessionContext(SessionContext sc) {
        this.sc = sc;
    }
    
    public void ejbRemove() throws RemoteException {}
    
    //empty for Stateless EJBs
    public void ejbActivate() {}

    //empty for Stateless EJBs
    public void ejbPassivate() {}
}
