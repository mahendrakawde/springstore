/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: OtEndpointBean.java,v 1.3 2004/08/06 20:28:13 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.otwebservice;

import javax.ejb.*;
import java.rmi.RemoteException; 
import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;
import com.sun.j2ee.blueprints.processmanager.ejb.*;
import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.opc.purchaseorder.*;
import com.sun.j2ee.blueprints.opc.purchaseorder.ejb.*;

/**
 *  This class is used to get Order Tracking info
 *  by adventure builder web site application after a user 
 *  has submitted an order, and wants to track it.
 */
public class OtEndpointBean implements SessionBean {

    private SessionContext sc;
    private ProcessManagerLocal processManager = null;
    private PurchaseOrderLocalHome poHome = null;
 
    public OtEndpointBean(){}
    
    public void ejbCreate() throws CreateException {   
  try {
      ServiceLocator sl = new ServiceLocator();
      ProcessManagerLocalHome pmHome =
    (ProcessManagerLocalHome)sl.getLocalHome(JNDINames.PM_EJB);
      processManager = pmHome.create();
      poHome = (PurchaseOrderLocalHome) sl.getLocalHome(JNDINames.PO_EJB);
  } catch (ServiceLocatorException se) {
          throw new CreateException(se.getMessage());
       }
    }

    /**
     * Accept an order id, and return the details of the current status
     * for the order.
     *
     * @return OrderDetails if orderId exists, else return null to 
     * indicate orderId not found
     */
    public OrderDetails getOrderDetails(String orderId)
                            throws OrderNotFoundException, RemoteException {
       
        OrderDetails details = new OrderDetails();
        try {
            String status = processManager.getOrderStatus(orderId);   
            details.setStatus(status);   
            PurchaseOrderLocal polocal = poHome.findByPrimaryKey(orderId);
        details.setPO(polocal.getPO());
        } catch (FinderException fe) {
      throw new OrderNotFoundException("Unable to locate order with id " + orderId + "; Please ensure that you entered the correcr order Id");
  }
        return details;
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
