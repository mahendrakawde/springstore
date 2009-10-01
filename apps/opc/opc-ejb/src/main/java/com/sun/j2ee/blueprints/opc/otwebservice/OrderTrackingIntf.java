/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: OrderTrackingIntf.java,v 1.3 2004/07/27 21:23:49 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.opc.otwebservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the web service endpoint of the 
 * OPC for Order Tracking.
 * @see com.sun.j2ee.blueprints.opc.otwebservice.OtEndpointBean 
 *      for the implementation of the interface
 */
public interface OrderTrackingIntf extends Remote {

    public OrderDetails getOrderDetails(String OrderId)
                             throws OrderNotFoundException, RemoteException;
}

