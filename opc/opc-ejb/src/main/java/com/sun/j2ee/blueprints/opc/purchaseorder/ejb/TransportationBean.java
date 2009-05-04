/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: TransportationBean.java,v 1.2 2004/05/26 00:06:58 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Implementation class for the  TransportationBean .
 * TransportationBean is a CMP Entity Bean
 **/

public abstract class TransportationBean   implements EntityBean {

  private EntityContext entityContext = null;

  public Object ejbCreate(Transportation transportation) throws CreateException {
    setTransportationId(transportation.getTransportationId());
    setCarrier(transportation.getCarrier());
    setOrigin(transportation.getOrigin());
    setDestination(transportation.getDestination());
    setDepartureDate(transportation.getDepartureDate().getTimeInMillis());
    setDepartureTime(transportation.getDepartureTime());
    setPrice(transportation.getPrice());
    setTravelClass(transportation.getTravelClass());
    setHeadCount(transportation.getHeadCount());

    return null;
  }

  public void ejbPostCreate(Transportation transportation) throws
      CreateException {

  }

  //getters and setters for CMP fields
  public abstract void setTransportationId(String transportationId);

  public abstract void setCarrier(String carrier);

  public abstract void setOrigin(String origin);

  public abstract void setDestination(String destination);

  public abstract void setDepartureDate(long departureDate);

  public abstract void setDepartureTime(String departureTime);

  public abstract void setPrice(float price);

  public abstract void setTravelClass(String travelClass);

  public abstract void setHeadCount(int headCount);

  public abstract String getTransportationId();

  public abstract String getCarrier();

  public abstract String getOrigin();

  public abstract String getDestination();

  public abstract long getDepartureDate();

  public abstract String getDepartureTime();

  public abstract float getPrice();

  public abstract String getTravelClass();

  public abstract int getHeadCount();

  public Transportation getDetails() {

    Transportation transportation = new Transportation();
    transportation.setTransportationId(getTransportationId());
    transportation.setCarrier(getCarrier());
    transportation.setOrigin(getOrigin());
    transportation.setDestination(getDestination());
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(getDepartureDate());
    transportation.setDepartureDate(cal);
    transportation.setDepartureTime(getDepartureTime());
    transportation.setPrice(getPrice());
    transportation.setTravelClass(getTravelClass());
    transportation.setHeadCount(getHeadCount());

    return transportation;
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
