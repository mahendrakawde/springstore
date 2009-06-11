/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ManagerBean.java,v 1.2 2004/05/26 00:07:07 inder Exp $ */

package com.sun.j2ee.blueprints.processmanager.manager.ejb;

import javax.ejb.*;
import java.util.*;

import com.sun.j2ee.blueprints.servicelocator.ejb.*;

public abstract class ManagerBean implements EntityBean {
    
    private EntityContext context = null;
    
    // CMP fields
    public abstract String getOrderId();
    public abstract void setOrderId(String orderId);
    
    public abstract String getStatus();
    public abstract void setStatus(String status);

    public abstract String getActivityOrderStatus();
    public abstract void setActivityOrderStatus(String status);

    public abstract String getAirlineOrderStatus();
    public abstract void setAirlineOrderStatus(String status);

    public abstract String getLodgingOrderStatus();
    public abstract void setLodgingOrderStatus(String status);

    public abstract boolean getOrderError();
    public abstract void setOrderError(boolean orderError);
  
    // EJB create methods
    public String ejbCreate(String orderId, String status, 
          String actyOrderStatus,
          String airlineOrderStatus, String lodgOrderStatus,
          boolean orderError) throws CreateException {
        setOrderId(orderId);
        setStatus(status);
        setActivityOrderStatus(actyOrderStatus);
        setAirlineOrderStatus(airlineOrderStatus);
        setLodgingOrderStatus(lodgOrderStatus);
        setOrderError(orderError);
        return null;
    }
    
    public void ejbPostCreate(String orderId, String status, String actyOrderStatus,
            String airlineOrderStatus, String lodgOrderStatus,boolean orderError) throws CreateException {}
          
    public void setEntityContext(EntityContext c) {
        context = c;
    }
    public void unsetEntityContext() {
        context = null;
    }
    public void ejbRemove() throws RemoveException {
    }
    public void ejbActivate() {
    }
    public void ejbPassivate() {
    }
    public void ejbStore() {
    }
    public void ejbLoad() {
    }
}
