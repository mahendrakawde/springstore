/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingOrderBean.java,v 1.4 2004/05/26 00:06:42 inder Exp $ */

package com.sun.j2ee.blueprints.lodgingsupplier.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

import com.sun.j2ee.blueprints.lodgingsupplier.powebservice.*;

/**
 * Implementation class for the  LodgingOrderBean .
 */
public abstract class LodgingOrderBean implements EntityBean {

    private EntityContext entityContext = null;

    public String ejbCreate(LodgingOrder lodgeObj) throws CreateException {

  setOrderId(lodgeObj.getOrderId());
  setLodgingId(lodgeObj.getLodgingId());
  setStartDate(lodgeObj.getStartDate().getTimeInMillis());
  setEndDate(lodgeObj.getEndDate().getTimeInMillis());
  setHeadCount(lodgeObj.getHeadCount());
  return null;
    }

    public void ejbPostCreate(LodgingOrder po) throws CreateException {}

    //getters and setters for CMP  fields
    public abstract void setOrderId(String orderId);
    public abstract void setLodgingId(String poId);
    public abstract void setStartDate(long date);
    public abstract void setEndDate(long date);
    public abstract void setHeadCount(int count);

    public abstract String getOrderId();
    public abstract String getLodgingId();
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
