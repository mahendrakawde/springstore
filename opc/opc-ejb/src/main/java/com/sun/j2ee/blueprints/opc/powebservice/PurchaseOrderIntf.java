/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: PurchaseOrderIntf.java,v 1.3 2004/05/26 00:06:53 inder Exp $ */

package com.sun.j2ee.blueprints.opc.powebservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;
import com.sun.j2ee.blueprints.opc.serviceexceptions.*;

/**
 * Interface for the PO web service endpoint of the 
 * Order Processing Center.
 */
public interface PurchaseOrderIntf extends Remote {
    public String submitPurchaseOrder(PurchaseOrder poObject)
           throws InvalidPOException, ProcessingException, RemoteException;
}

