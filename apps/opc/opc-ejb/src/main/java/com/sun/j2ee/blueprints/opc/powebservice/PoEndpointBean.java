/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: PoEndpointBean.java,v 1.4 2004/05/26 00:06:53 inder Exp $ */

package com.sun.j2ee.blueprints.opc.powebservice;

import java.util.*;
import java.rmi.*;

import javax.ejb.*;
import javax.jms.*;

import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;
import com.sun.j2ee.blueprints.opc.purchaseorder.*;
import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.opc.utils.*;
import com.sun.j2ee.blueprints.opc.serviceexceptions.*;

/**
 *  This class is the entry point for purchase orders submitted 
 *  by adventure builder web site application when a user 
 *  submits an order.
 */
public class PoEndpointBean implements SessionBean {

    private SessionContext sc;
 
    public PoEndpointBean(){}
    
    public void ejbCreate() throws CreateException {}

    /**
     * Accept a purchase order, place the order in a JMS queue and return the 
     * order id so that the caller can have a correlation id for the order
     */
    public String submitPurchaseOrder(PurchaseOrder po)
  throws InvalidPOException, ProcessingException, RemoteException { 

        //validate PO, make sure all required info is provided.
        if (po == null) {
            throw new InvalidPOException("The Purchase Order received was empty!!!!");
        } else if ( po.getUserId()       == null || 
                    po.getEmailId()      == null || 
                    po.getLocale()       == null || 
                    po.getOrderDate()    == null ||
                    po.getShippingInfo() == null ||
                    po.getBillingInfo()  == null ||
                    po.getTotalPrice()   == 0    ||
                    po.getCreditCard()   == null ||
                    po.getHeadCount()    == 0    ||
                    po.getStartDate()    == null ||
                    po.getEndDate()      == null ||
                    po.getDepartureCity()== null  ) {
            throw new InvalidPOException("No field in the purchase order can be null!");
        }
  if(JMSUtils.sendMessage(JNDINames.WORKFLOW_MGR_MDB_QUEUE, 
        JNDINames.DOC_TYPE, JNDINames.PO_DOCUMENT, 
        (Object)po) == false)
      throw new ProcessingException("Irrecoverable error while submitting the order for processing");
        return po.getPoId();
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
