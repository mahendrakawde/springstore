/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ProcessManagerLocal.java,v 1.3 2004/08/06 20:29:15 smitha Exp $ */

package com.sun.j2ee.blueprints.processmanager.ejb;

import javax.ejb.*;
import java.util.*;

/**
 * This interface provides methods to view and modify
 * information for a particular order workflow process.
*/
public interface ProcessManagerLocal extends EJBLocalObject {

    /** 
     * update the status of the order corresponding to orderId     
     */
    public void updateStatusToCompleted (String orderId) 
                                     throws FinderException ;
    public void updateStatus (String orderId, String status) 
                                     throws FinderException ;
    public void updateOrderErrorStatus(String orderId, boolean error)
                               throws FinderException;
    public void updateActivityOrderStatus(String orderId, String status)
                                     throws FinderException ;
    public void updateAirlineOrderStatus(String orderId, String status)
                                     throws FinderException ;
    public void updateLodgingOrderStatus(String orderId, String status) 
                                     throws FinderException ;
    
    /**
     * start a new workflow for each new order
     */
    public void createManager (String orderId, String status, 
             String actyOrderStatus,
             String airlineOrderStatus, 
             String lodgOrderStatus) throws CreateException;
    
    public String getOrderStatus(String orderId) throws FinderException;

    public Collection getOrdersByStatus(String status) throws FinderException;
}

