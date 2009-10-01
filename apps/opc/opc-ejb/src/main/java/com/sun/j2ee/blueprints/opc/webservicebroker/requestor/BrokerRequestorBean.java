/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: BrokerRequestorBean.java,v 1.5 2004/05/26 00:07:02 inder Exp $ */

package com.sun.j2ee.blueprints.opc.webservicebroker.requestor;

import javax.jms.*;
import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.JNDINames;

public class BrokerRequestorBean implements
       MessageDrivenBean, MessageListener {

    private  MessageDrivenContext context; 

    public void setMessageDrivenContext(MessageDrivenContext context) {
        this.context=context;
    }  
      
    public void ejbCreate() {}
      
    public void onMessage(Message message) {
  WSClient target = null;

  try {
      // Step 1 : Retrieve property that indicates to whom this doc is 
      // to be sent
      String docType = message.getStringProperty(JNDINames.DOC_TYPE);
            
      // Step 2 : Get the client implementation through the factory
      if(docType.equals(JNDINames.ACTIVITY_ORDER))
    target = WSClientFactory.getWSClient(JNDINames.ACTIVITY_SUPPLIER_CLIENT);
      else if(docType.equals(JNDINames.LODGING_ORDER))
    target = WSClientFactory.getWSClient(JNDINames.LODGING_SUPPLIER_CLIENT);
      else if(docType.equals(JNDINames.AIRLINE_ORDER))
    target = WSClientFactory.getWSClient(JNDINames.AIRLINE_SUPPLIER_CLIENT);

      // Step 3 : Send the document
      if(target != null) {
    TextMessage msg = (TextMessage) message;
    String po = msg.getText();
    String retVal = target.sendRequest(po);
      }
  } catch (JMSException jex) {
      throw new EJBException(jex);
  }
    }
    
    public void ejbRemove() {}
}

