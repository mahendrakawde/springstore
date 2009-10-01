/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityDetailsBean.java,v 1.3 2004/05/26 00:05:59 inder Exp $ */

package com.sun.j2ee.blueprints.activitysupplier.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

/**
 * Implementation class for the  ActivityDetailsBean .
 **/

public abstract class ActivityDetailsBean implements EntityBean {

    private EntityContext entityContext = null;

    public Object ejbCreate(String id, Calendar start, Calendar end,
          int count) throws CreateException {

  setActivityId(id);
  setStartDate(start.getTimeInMillis());
  setEndDate(end.getTimeInMillis());
  setHeadCount(count);
  return null;
    }

    public void ejbPostCreate(String id, Calendar start, Calendar end,
          int count) throws CreateException {}

    //getters and setters for CMP  fields
    public abstract void setActivityId(String poId);
    public abstract void setStartDate(long date);
    public abstract void setEndDate(long date);
    public abstract void setHeadCount(int count);

    public abstract String getActivityId();
    public abstract long getStartDate();
    public abstract long getEndDate();
    public abstract int getHeadCount();

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
