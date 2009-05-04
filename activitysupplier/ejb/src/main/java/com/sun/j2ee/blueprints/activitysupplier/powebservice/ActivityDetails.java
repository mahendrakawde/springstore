/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityDetails.java,v 1.2 2004/05/26 00:05:58 inder Exp $ */

package com.sun.j2ee.blueprints.activitysupplier.powebservice;

import java.util.*;

public class ActivityDetails implements java.io.Serializable {

    protected String activityId;
    protected Calendar startDate;
    protected Calendar endDate;
    protected int headCount;

    // Constructor
    public ActivityDetails() {}

    public ActivityDetails(String activityId, Calendar startDate,
       Calendar endDate, int headCount) {
  this.activityId = activityId;
  this.startDate = startDate;
  this.endDate = endDate;
  this.headCount = headCount;
    }

    // getter methods
    public String getActivityId() {
  return activityId;
    }

    public Calendar getStartDate() {
  return startDate;
    }

    public Calendar getEndDate() {
  return endDate;
    }

    public int getHeadCount() {
  return headCount;
    }

    // setter methods
    public void setActivityId(String id) {
  this.activityId = id;
    }

    public void setStartDate(Calendar startDate) {
  this.startDate = startDate;
    }

    public void setEndDate(Calendar endDate) {
  this.endDate = endDate;
    }

    public void setHeadCount(int headCount) {
  this.headCount = headCount;
    }
}
