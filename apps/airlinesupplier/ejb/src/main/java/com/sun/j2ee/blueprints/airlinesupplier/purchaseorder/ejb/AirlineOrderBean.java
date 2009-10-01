/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AirlineOrderBean.java,v 1.4 2004/05/26 00:06:06 inder Exp $ */

package com.sun.j2ee.blueprints.airlinesupplier.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

import com.sun.j2ee.blueprints.airlinesupplier.powebservice.*;

/**
 * Implementation class for the  AirlineOrderBean .
 */
public abstract class AirlineOrderBean implements EntityBean {

    private EntityContext entityContext = null;

    public String ejbCreate(AirlineOrder flightObj) throws CreateException {

  setOrderId(flightObj.getOrderId());
  setDepFlightId(flightObj.getDepFlightId());
  setDepFlightDate(flightObj.getDepFlightDate().getTimeInMillis());
  setRetFlightId(flightObj.getRetFlightId());
  setRetFlightDate(flightObj.getRetFlightDate().getTimeInMillis());
  setHeadCount(flightObj.getHeadCount());
  return null;
    }

    public void ejbPostCreate(AirlineOrder po) throws CreateException {}

    //getters and setters for CMP  fields
    public abstract void setOrderId(String orderId);
    public abstract void setDepFlightId(String flightId);
    public abstract void setDepFlightDate(long date);
    public abstract void setRetFlightId(String flightId);
    public abstract void setRetFlightDate(long date);
    public abstract void setHeadCount(int count);

    public abstract String getOrderId();
    public abstract String getDepFlightId();
    public abstract long getDepFlightDate();
    public abstract String getRetFlightId();
    public abstract long getRetFlightDate();
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
