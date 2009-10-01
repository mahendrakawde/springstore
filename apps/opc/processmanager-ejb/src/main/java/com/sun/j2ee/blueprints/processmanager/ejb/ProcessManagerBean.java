/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ProcessManagerBean.java,v 1.3 2004/08/06 20:29:14 smitha Exp $ */

package com.sun.j2ee.blueprints.processmanager.ejb;

import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.ejb.DuplicateKeyException;

import com.sun.j2ee.blueprints.processmanager.manager.ejb.ManagerLocal;
import com.sun.j2ee.blueprints.processmanager.manager.ejb.ManagerLocalHome;

import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;

public class ProcessManagerBean  implements SessionBean {
    
    public static final String MANAGER_HOME_ENV_NAME =
        "java:comp/env/ejb/local/processmanager/Manager";
    
    private ManagerLocalHome mlh;
   
    /**
     * Business method used for when new purchase orders are recieved 
     * and want to start the workflow process to fullfil the order
     */
    public void createManager(String orderId, String status, 
            String actyOrderStatus, 
            String airlineOrderStatus, 
            String lodgOrderStatus) 
                                       throws CreateException {       
  ManagerLocal manager = mlh.create(orderId, status, actyOrderStatus, 
            airlineOrderStatus, lodgOrderStatus,
            false);            
    }

    /**
     * Business methods used to keep track of an order in the workflow process
     */   

    public void updateStatus(String orderId, String status) 
  throws FinderException {
  ManagerLocal manager = mlh.findByPrimaryKey(orderId);
  manager.setStatus(status);
    }

    public void updateOrderErrorStatus(String orderId, boolean error) 
  throws FinderException {
  ManagerLocal manager = mlh.findByPrimaryKey(orderId);
  manager.setOrderError(error);
    }

    public void updateActivityOrderStatus(String orderId, String status) 
  throws FinderException {                                   
  ManagerLocal manager = mlh.findByPrimaryKey(orderId);
  manager.setActivityOrderStatus(status);
    }

    public void updateAirlineOrderStatus(String orderId, String status) 
  throws FinderException {                              
  ManagerLocal manager = mlh.findByPrimaryKey(orderId);
  manager.setAirlineOrderStatus(status);  
    }
    
    public void updateLodgingOrderStatus(String orderId, String status) 
  throws FinderException {                              
  ManagerLocal manager = mlh.findByPrimaryKey(orderId);
  manager.setLodgingOrderStatus(status);   
    }

    public void updateStatusToCompleted(String orderId) throws FinderException{
      //change the order status to completed if all the three suborders
      //are completed
      
  if ( (getActivityOrderStatus(orderId).equalsIgnoreCase(OrderStatusNames.COMPLETED)) &&
       (getAirlineOrderStatus(orderId).equalsIgnoreCase(OrderStatusNames.COMPLETED)) &&
       (getLodgingOrderStatus(orderId).equalsIgnoreCase(OrderStatusNames.COMPLETED))) {   
         ManagerLocal manager = mlh.findByPrimaryKey(orderId);
         manager.setStatus("COMPLETED");
        }
    }


    /**
     * Business methods used to keep track of an order in the workflow process
     */
    public String getOrderStatus(String orderId) throws FinderException {                              
  ManagerLocal manager = mlh.findByPrimaryKey(orderId);
  return manager.getStatus();
    }

    private String getActivityOrderStatus(String orderId) 
  throws FinderException {                              
  ManagerLocal manager = mlh.findByPrimaryKey(orderId);
  return manager.getActivityOrderStatus();
    }

    private String getAirlineOrderStatus(String orderId) 
  throws FinderException {                              
  ManagerLocal manager = mlh.findByPrimaryKey(orderId);
  return manager.getAirlineOrderStatus();
    }

    private String getLodgingOrderStatus(String orderId) 
  throws FinderException {                              
  ManagerLocal manager = mlh.findByPrimaryKey(orderId);
  return manager.getLodgingOrderStatus();
    }



    /**
     * Business method to get all orders of given status for the admin
     */
    public Collection getOrdersByStatus (String status) throws FinderException {
        return mlh.findOrdersByStatus(status);
    }

    public void ejbCreate() throws CreateException {
  try {
      ServiceLocator sl = new ServiceLocator();
      mlh = (ManagerLocalHome) sl.getLocalHome(MANAGER_HOME_ENV_NAME);
  } catch (ServiceLocatorException se) {
      throw new EJBException("Got service locator exception! " + 
           se.getMessage());
  }
    }
    
    // Misc Method
    //=============
    //activate is empty for stateless session EJBs
    public void ejbActivate() { }

    //passivate is empty for stateless session EJBs
    public void ejbPassivate() { }
 
    public void setSessionContext(SessionContext c) { }
    public void ejbRemove() { }

}
