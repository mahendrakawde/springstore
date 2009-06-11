/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingLocal.java,v 1.2 2004/05/26 00:06:57 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;


/**
 * Local Interface for the LodgingBean.
 **/

public interface LodgingLocal  extends EJBLocalObject {

  public void setLodgingId(String lodgingId);

  public String getLodgingId();

  public void setName(String name);

  public String getName();

  public void setPricePerNight(float pricePerNight);

  public float getPricePerNight();

  public void setLocation(String location);

  public String getLocation();

  public void setStartDate(long startDate);

  public long getStartDate();

  public void setEndDate(long endDate);

  public long getEndDate();

  public void setNoNights(int noNights);

  public int getNoNights();

  public void setNoRooms(int noRooms);

  public int getNoRooms();

  public Lodging getDetails();

}
