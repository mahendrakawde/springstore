/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingMessageBean.java,v 1.9 2005/03/08 00:19:15 smitha Exp $ */
package com.sun.j2ee.blueprints.lodgingsupplier.pomessagebean;

import java.io.Serializable;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.naming.*;
import javax.jms.*;
import javax.xml.rpc.*;

import com.sun.j2ee.blueprints.lodgingsupplier.JNDINames;
import com.sun.j2ee.blueprints.lodgingsupplier.powebservice.*;
import com.sun.j2ee.blueprints.lodgingsupplier.purchaseorder.ejb.*;
import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;

public class LodgingMessageBean implements MessageDrivenBean, MessageListener {

    private transient MessageDrivenContext mdc = null;

    /**
     * Default constructor.
     */
    public LodgingMessageBean() {}

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
        LodgingOrder lo = null;

        try {
      String messageID = message.getJMSMessageID();
            if (message instanceof ObjectMessage) {
                ObjectMessage msg = (ObjectMessage)message;
    lo = (LodgingOrder)msg.getObject();
      } else {
                System.out.println("Wrong type message: "
                                   + message.getClass().getName());
            }
        } catch (JMSException e) {
      // Proper exception handling as in OPC module has to be 
      // implemented here later
            e.printStackTrace();
        }

  try {
      doWork(lo);
  } catch (OrderSubmissionException oe) {
      // Proper exception handling as in OPC module has to be 
      // implemented here later
      oe.printStackTrace();
  }

    }

    public void doWork(LodgingOrder lodge) throws OrderSubmissionException {
  try {
      persistOrder(lodge);
  } catch (Exception e) {
      // Proper exception handling as in OPC module has to be 
      // implemented here later
      e.printStackTrace();
  }
  sendInvoice(lodge);
    }

    public void sendInvoice(LodgingOrder lodge) {
  Invoice inv = new Invoice("1234", lodge.getOrderId(), 
          "LODGING_INVOICE", "COMPLETED",
          lodge.getLodgingId().trim(),
          "1234 Main Street, Sometown 12345, USA",
          "No Cancelations 24 hours prior");
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
     * Persists the LodgingOrder
     */
    public void persistOrder(LodgingOrder lodge)
  throws OrderSubmissionException {

  try {
            ServiceLocator sl = new ServiceLocator();

            LodgingOrderLocalHome lodgeLocalHome = (LodgingOrderLocalHome)
                sl.getLocalHome(JNDINames.LODGE_ORDER_EJB);
            LodgingOrderLocal  lodgeLocal =
                (LodgingOrderLocal) lodgeLocalHome.create(lodge);
        } catch (ServiceLocatorException je) {
            throw new OrderSubmissionException("Error LODGE persisting order:"
                                               + je.getMessage());
        } catch(CreateException ce) {
            throw new OrderSubmissionException("Error LODGE persisting order:"
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
