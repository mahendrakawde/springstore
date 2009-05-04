/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: PurchaseOrderLocal.java,v 1.4 2004/06/08 23:47:57 yutayoshida Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Local Interface for the PurchaseOrderBean.
 **/

public interface PurchaseOrderLocal  extends EJBLocalObject {

  public String getPoId();

  public void setShippingInfo(ContactInfoLocal shippingInfo);

  public ContactInfoLocal getShippingInfo();

  public void setUserId(String userId);

  public String getUserId();

  public void setEmailId(String emailId);

  public String getEmailId();

  public void setLocale(String locale);

  public String getLocale();

  public void setOrderDate(long orderDate);

  public long getOrderDate();

  public void setTotalPrice(float totalPrice);

  public float getTotalPrice();

  public void setHeadCount(int headCount);

  public int getHeadCount();

  public void setStartDate(long startDate);

  public long getStartDate();

  public void setEndDate(long endDate);

  public long getEndDate();

  public void setDepartureCity(String departureCity);

  public String getDepartureCity();

  public void setCreditCard(CreditCardLocal creditCard);

  public CreditCardLocal getCreditCard();

  public void setLodging(LodgingLocal lodging);

  public LodgingLocal getLodging();

  public void setBillingInfo(ContactInfoLocal billingInfo);

  public ContactInfoLocal getBillingInfo();

  public void setDepartureFlightInfo(TransportationLocal departureFlightInfo);

  public TransportationLocal getDepartureFlightInfo();

  public void setReturnFlightInfo(TransportationLocal returnFlightInfo);

  public TransportationLocal getReturnFlightInfo();

  public void setActivities(Collection activities);

  public Collection getActivities();

  public void addActivity(ActivityLocal activity);

  public PurchaseOrder getPO() ;

}
