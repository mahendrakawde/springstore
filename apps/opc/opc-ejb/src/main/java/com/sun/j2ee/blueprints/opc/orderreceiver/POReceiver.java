/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: POReceiver.java,v 1.4 2004/05/26 00:06:51 inder Exp $ */

package com.sun.j2ee.blueprints.opc.orderreceiver;

import javax.ejb.CreateException;

import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.opc.purchaseorder.PurchaseOrder;
import com.sun.j2ee.blueprints.opc.purchaseorder.ejb.PurchaseOrderLocal;
import com.sun.j2ee.blueprints.opc.purchaseorder.ejb.PurchaseOrderLocalHome;
import com.sun.j2ee.blueprints.servicelocator.ServiceLocatorException;
import com.sun.j2ee.blueprints.servicelocator.ejb.ServiceLocator;

/**
 * This component receives and persists a 
 * purchase order
 */
public class POReceiver {
    
    private PurchaseOrderLocalHome poHome;
    
    public POReceiver() throws ServiceLocatorException {      
        ServiceLocator sl = new ServiceLocator(); 
        poHome = (PurchaseOrderLocalHome) sl.getLocalHome(JNDINames.PO_EJB);
    }
    
    public void persistPO(PurchaseOrder po) throws CreateException{      
        // persist the PO    
        PurchaseOrderLocal polocal = poHome.create(po);             
    }
}
