/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ManagerLocal.java,v 1.2 2004/05/26 00:07:07 inder Exp $ */

package com.sun.j2ee.blueprints.processmanager.manager.ejb;

/**
 * This interface provides methods to view and modify sign on
 * information for a particular manager.
 */
public interface ManagerLocal extends javax.ejb.EJBLocalObject {
            
    public String getOrderId();
    
    public String getStatus();
    public void setStatus(String status);

    public boolean getOrderError();
    public void setOrderError(boolean error);

    public String getActivityOrderStatus();
    public void setActivityOrderStatus(String status);

    public String getAirlineOrderStatus();
    public void setAirlineOrderStatus(String status);

    public String getLodgingOrderStatus();
    public void setLodgingOrderStatus(String status);
    
}

