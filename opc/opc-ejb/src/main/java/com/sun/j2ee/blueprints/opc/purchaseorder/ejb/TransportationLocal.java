/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: TransportationLocal.java,v 1.2 2004/05/26 00:06:58 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Local Interface for the TransportationBean.
 **/

public interface TransportationLocal extends EJBLocalObject {

  public void setTransportationId(String transportationId);

  public String getTransportationId();

  public void setCarrier(String carrier);

  public String getCarrier();

  public void setOrigin(String origin);

  public String getOrigin();

  public void setDestination(String destination);

  public String getDestination();

  public void setDepartureDate(long departureDate);

  public long getDepartureDate();

  public void setDepartureTime(String departureTime);

  public String getDepartureTime();

  public void setPrice(float price);

  public float getPrice();

  public void setTravelClass(String travelClass);

  public String getTravelClass();

  public void setHeadCount(int headCount);

  public int getHeadCount();

  public Transportation getDetails();
}
