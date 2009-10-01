/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AirlineMessageBean.java,v 1.8 2005/03/08 00:19:31 smitha Exp $ */
package com.sun.j2ee.blueprints.airlinesupplier.pomessagebean;

import java.io.Serializable;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.naming.*;
import javax.jms.*;
import javax.xml.rpc.*;

import com.sun.j2ee.blueprints.airlinesupplier.JNDINames;
import com.sun.j2ee.blueprints.airlinesupplier.powebservice.*;
import com.sun.j2ee.blueprints.airlinesupplier.purchaseorder.ejb.*;
import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;

public class AirlineMessageBean implements MessageDrivenBean, MessageListener {

    private transient MessageDrivenContext mdc = null;

    /**
     * Default constructor.
     */
    public AirlineMessageBean() {}

    /**
     * Sets the context for this bean.
     */
    public void setMessageDrivenContext (MessageDrivenContext mdc) {
  this.mdc = mdc;
    }

    /**
     * Casts the incoming message to an ObjectMessage.
     */
    public void onMessage(Message message) {
        AirlineOrder alo = null;

        try {
      String messageID = message.getJMSMessageID();
            if (message instanceof ObjectMessage) {
                ObjectMessage msg = (ObjectMessage)message;
    alo = (AirlineOrder)msg.getObject();
      } else {
                System.out.println("Wrong type message for AL order: "
                                   + message.getClass().getName());
            }
        } catch (JMSException e) {
      // Proper exception handling as in OPC module has to be 
      // implemented here later
            e.printStackTrace();
        }
  try {
      doWork(alo);
  } catch (OrderSubmissionException oe) {
      // Proper exception handling as in OPC module has to be 
      // implemented here later
      oe.printStackTrace();
  }

    }

    public void doWork(AirlineOrder flight) throws OrderSubmissionException {
  try {
      persistOrder(flight);
  } catch (Exception e) {
      // Proper exception handling as in OPC module has to be 
      // implemented here later
      e.printStackTrace();
  }
  sendInvoice(flight);
    }

    public void sendInvoice(AirlineOrder flight) {
  Invoice inv = new Invoice("1234", flight.getOrderId(),
          "AIRLINE_INVOICE", "AGENT-001234",
          flight.getDepFlightDate(),
          flight.getRetFlightDate(),
          flight.getDepFlightId(),
          flight.getRetFlightId(),
          flight.getHeadCount(),
          "COMPLETED",
          "48 Hours Prior to Flight");
  try {
      InitialContext ic = new InitialContext();
      WebServiceBroker svc = (WebServiceBroker) 
    ic.lookup(JNDINames.BROKER_SERVICE_NAME);
      BrokerServiceIntf port= (BrokerServiceIntf)
                     svc.getPort(BrokerServiceIntf.class);      
      port.submitDocument(inv.toXML());
  } catch (Exception ne) {
      // Proper exception handling as in OPC module has to be 
      // implemented here later
      ne.printStackTrace();
  }
    }
  

    /**
     * Persists the AirlineOrder
     */
    public void persistOrder(AirlineOrder flight)
  throws OrderSubmissionException {

  try {
            ServiceLocator sl = new ServiceLocator();

            AirlineOrderLocalHome flightLocalHome = (AirlineOrderLocalHome)
                sl.getLocalHome(JNDINames.AIRLINE_ORDER_EJB);
            AirlineOrderLocal  flightLocal =
                (AirlineOrderLocal) flightLocalHome.create(flight);
        } catch (ServiceLocatorException je) {
            throw new OrderSubmissionException("Error AL persisting order:"
                                               + je.getMessage());
        } catch(CreateException ce) {
            throw new OrderSubmissionException("Error AL persisting order:"
                                               + ce.getMessage());
        }
    }


    /**
     * Creates a bean.
     */
    public void ejbCreate() {}

    /**
     * Removes this bean.
     */
    public void ejbRemove() {
        mdc = null;
    }
}
