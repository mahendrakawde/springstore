/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityLocal.java,v 1.2 2004/05/26 00:06:55 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Local Interface for the ActivityBean.
 **/

public interface ActivityLocal  extends EJBLocalObject {

  public void setActivityId(String activityId);

  public String getActivityId();

  public void setName(String name);

  public String getName();

  public void setPrice(float price);

  public float getPrice();

  public void setLocation(String location);

  public String getLocation();

  public void setStartDate(long startDate);

  public long getStartDate();

  public void setEndDate(long endDate);

  public long getEndDate();

  public void setHeadCount(int headCount);

  public int getHeadCount();

  public Activity getDetails();

}
