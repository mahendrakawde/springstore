/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ManagerLocalHome.java,v 1.2 2004/05/26 00:07:07 inder Exp $ */

package com.sun.j2ee.blueprints.processmanager.manager.ejb;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

import java.util.Collection;

/**
 * The home interface of the Manager Entity EJB.
 */

public interface ManagerLocalHome extends javax.ejb.EJBLocalHome {

    public ManagerLocal create(String orderId, String status,String actyOrderStatus,
           String airlineOrderStatus, String lodgOrderStatus, boolean orderError) throws CreateException;

    public ManagerLocal findByPrimaryKey (String orderId) throws FinderException;
    
    public Collection findOrdersByStatus(String status) throws FinderException;

}

