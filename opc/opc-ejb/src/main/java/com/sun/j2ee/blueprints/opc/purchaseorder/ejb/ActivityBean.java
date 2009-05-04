/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityBean.java,v 1.2 2004/05/26 00:06:55 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Implementation class for the  ActivityBean .
 * ActivityBean is a CMP Entity Bean
 **/

public  abstract  class ActivityBean   implements EntityBean {

  private EntityContext entityContext = null;

  public Object ejbCreate(Activity activity) throws CreateException {
    setActivityId(activity.getActivityId());
    setName(activity.getName());
    setPrice(activity.getPrice());
    setLocation(activity.getLocation());
    setStartDate(activity.getStartDate().getTimeInMillis());
    setEndDate(activity.getEndDate().getTimeInMillis());
    setHeadCount(activity.getHeadCount());

    return null;

  }

  public void ejbPostCreate(Activity activity) throws CreateException {

  }

  //getters and setters for CMP fields
  public abstract void setActivityId(String activityId);

  public abstract void setName(String name);

  public abstract void setPrice(float price);

  public abstract void setLocation(String location);

  public abstract void setStartDate(long startDate);

  public abstract void setEndDate(long endDate);

  public abstract void setHeadCount(int headCount);

  public abstract String getActivityId();

  public abstract String getName();

  public abstract float getPrice();

  public abstract String getLocation();

  public abstract long getStartDate();

  public abstract long getEndDate();

  public abstract int getHeadCount();

  public Activity getDetails() {

    Activity activity = new Activity();
    activity.setActivityId(getActivityId());
    activity.setName(getName());
    activity.setPrice(getPrice());
    activity.setLocation(getLocation());
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(getStartDate());
    activity.setStartDate(cal);
    cal.setTimeInMillis(getEndDate());
    activity.setEndDate(cal);
    activity.setHeadCount(getHeadCount());

    return activity;

  }


  public void ejbRemove() throws RemoveException {

  }

  public void ejbLoad() {

  }

  public void ejbStore() {

  }

  public void ejbActivate() {

  }

  public void ejbPassivate() {

  }

  public void unsetEntityContext() {
    this.entityContext = null;
  }
  public void setEntityContext(EntityContext entityContext) {
    this.entityContext = entityContext;
  }

}
