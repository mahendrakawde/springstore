/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityPurchaseOrderBean.java,v 1.2 2004/05/26 00:06:00 inder Exp $ */
package com.sun.j2ee.blueprints.activitysupplier.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

import com.sun.j2ee.blueprints.activitysupplier.powebservice.*;
import com.sun.j2ee.blueprints.activitysupplier.*;
import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;

/**
 * Implementation class for the  ActivityPurchaseOrderBean .
 * ActivityPurchaseOrderBean is a CMP Entity Bean representing
 * a purchase order . It has a  1:MANY  relationship with 
 * ActivityDetailsBean
 **/

public abstract class ActivityPurchaseOrderBean implements EntityBean {

    private EntityContext entityContext = null;

    public String ejbCreate(ActivityOrder po) throws CreateException {
  setPoId(po.getOrderId());
  return null;
    }

    public void ejbPostCreate(ActivityOrder po) throws CreateException {

  try {
      ServiceLocator sl = new ServiceLocator();
     
      //set activities
      if(po != null){
    ActivityDetailsLocalHome alh = (ActivityDetailsLocalHome)
        sl.getLocalHome(JNDINames.ACTIVITY_DETAILS_EJB);
    ArrayList activities = po.getActivities();      
    for(int i=0; i < activities.size(); i++) {
        ActivityDetails act = (ActivityDetails) activities.get(i);
        ActivityDetailsLocal al = (ActivityDetailsLocal) 
      alh.create(act.getActivityId(), act.getStartDate(),
           act.getEndDate(), act.getHeadCount());
        addActivity(al);
    }
      }
  } catch (ServiceLocatorException se) {
      throw new CreateException("Exception saving Activity PO:" +
              se.getMessage());
  }
    }
    
    //getters and setters for CMP  fields
    public abstract void setPoId(String poId);

    public abstract String getPoId();
    
    public abstract void setActivities(Collection activities);

    public abstract Collection getActivities();

    public void addActivity(ActivityDetailsLocal activity) {
  getActivities().add(activity);
    }

    public void ejbRemove() throws RemoveException {}

    public void ejbLoad() {}

    public void ejbStore() {}

    public void ejbActivate() {}

    public void ejbPassivate() {}

    public void unsetEntityContext() {
  this.entityContext = null;
    }

    public void setEntityContext(EntityContext entityContext) {
  this.entityContext = entityContext;
    }
}
