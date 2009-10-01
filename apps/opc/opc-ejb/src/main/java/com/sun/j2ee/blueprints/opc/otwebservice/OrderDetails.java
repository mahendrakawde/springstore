/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: OrderDetails.java,v 1.2 2004/05/26 00:06:51 inder Exp $ */

package com.sun.j2ee.blueprints.opc.otwebservice;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * This object is returned on web service requests to get
 * order tracking information about a particular order.
 * Since this is part of the web service interface, it would
 * be represented in the WSDL of the order tracking endpoint.
 */
public class OrderDetails {

    private String status;
    private PurchaseOrder po;

    // Constructor
    public OrderDetails() {}
    
    // getter methods
    public String getStatus() {
        return status;
    }
    
    public PurchaseOrder getPO() {
        return po;
    }
   
    // setter methods
    public void setStatus(String stat) {
        this.status = stat;
    }
    
    public void setPO(PurchaseOrder pOrder) {
        this.po = pOrder;
    }   
}
