/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingBean.java,v 1.2 2004/05/26 00:06:57 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Implementation class for the  LodgingBean .
 * LodgingBean is a CMP Entity Bean
 **/

public abstract  class LodgingBean implements EntityBean {

  private EntityContext entityContext = null;

  public Object ejbCreate(Lodging lodging) throws CreateException {

    setLodgingId(lodging.getLodgingId());
    setName(lodging.getName());
    setPricePerNight(lodging.getPricePerNight());
    setLocation(lodging.getLocation());
    setStartDate(lodging.getStartDate().getTimeInMillis());
    setEndDate(lodging.getEndDate().getTimeInMillis());
    setNoNights(lodging.getNoNights());
    setNoRooms(lodging.getNoRooms());

    return null;
  }
  public void ejbPostCreate(Lodging lodging) throws CreateException {

  }

  //geters and setters for CMP fields
  public abstract void setLodgingId(String lodgingId);
  public abstract void setName(String name);
  public abstract void setPricePerNight(float pricePerNight);
  public abstract void setLocation(String location);
  public abstract void setStartDate(long startDate);
  public abstract void setEndDate(long endDate);
  public abstract void setNoNights(int noNights);
  public abstract void setNoRooms(int noRooms);
  public abstract String getLodgingId();
  public abstract String getName();
  public abstract float getPricePerNight();
  public abstract String getLocation();
  public abstract long getStartDate();
  public abstract long getEndDate();
  public abstract int getNoNights();
  public abstract int getNoRooms();

  public Lodging getDetails() {

    Lodging lodging = new Lodging();
    lodging.setLodgingId(getLodgingId());
    lodging.setName(getName());
    lodging.setPricePerNight(getPricePerNight());
    lodging.setLocation(getLocation());
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(getStartDate());
    lodging.setStartDate(cal);
    cal.setTimeInMillis(getEndDate());
    lodging.setEndDate(cal);
    lodging.setNoNights(getNoNights());
    lodging.setNoRooms(getNoRooms());

    return lodging;

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
