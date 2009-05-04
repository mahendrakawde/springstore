/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityMessageBean.java,v 1.10 2005/03/08 00:19:47 smitha Exp $ */
package com.sun.j2ee.blueprints.activitysupplier.pomessagebean;

import java.io.Serializable;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.jms.*;
import javax.xml.rpc.*;
import javax.naming.*;

import com.sun.j2ee.blueprints.activitysupplier.JNDINames;
import com.sun.j2ee.blueprints.activitysupplier.powebservice.*;
import com.sun.j2ee.blueprints.activitysupplier.purchaseorder.ejb.*;
import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;

public class ActivityMessageBean implements 
         MessageDrivenBean, MessageListener {

    private transient MessageDrivenContext mdc = null;

    /**
     * Default constructor.
     */
    public ActivityMessageBean() {}

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
        ActivityOrder ao = null;

        try {
      String messageID = message.getJMSMessageID();
            if (message instanceof ObjectMessage) {
                ObjectMessage msg = (ObjectMessage)message;
    ao = (ActivityOrder)msg.getObject();
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
      doWork(ao);
  } catch (OrderSubmissionException oe) {
      // Proper exception handling as in OPC module has to be 
      // implemented here later
      oe.printStackTrace();
  }

    }

    private void doWork(ActivityOrder act) throws OrderSubmissionException {
  try {
      persistOrder(act);
  } catch (Exception e) {
      // Proper exception handling as in OPC module has to be 
      // implemented here later
      e.printStackTrace();
  }
  sendInvoice(act);
    }

    private void sendInvoice(ActivityOrder act) {
  Invoice inv = new Invoice("1234", act.getOrderId(), "ACTIVITY_INVOICE",
          act, "COMPLETED");
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
     * Persists the ActivityOrder
     */
    public void persistOrder(ActivityOrder act)
  throws OrderSubmissionException {

  try {
            ServiceLocator sl = new ServiceLocator();

            ActivityPurchaseOrderLocalHome actLocalHome = 
    (ActivityPurchaseOrderLocalHome)
                sl.getLocalHome(JNDINames.ACTIVITY_PURCHASEORDER_EJB);
            ActivityPurchaseOrderLocal  actLocal =
                (ActivityPurchaseOrderLocal) actLocalHome.create(act);
        } catch (ServiceLocatorException je) {
            throw new OrderSubmissionException("Error while persisting order:"
                                               + je.getMessage());
        } catch(CreateException ce) {
            throw new OrderSubmissionException("Error while persisting order:"
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
