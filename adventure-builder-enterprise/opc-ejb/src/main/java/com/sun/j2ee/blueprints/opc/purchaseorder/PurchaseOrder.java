/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: PurchaseOrder.java,v 1.2 2004/05/26 00:06:54 inder Exp $ */

package com.sun.j2ee.blueprints.opc.purchaseorder;

import java.util.*;
import java.io.*;

/**
 * This object is represents the purchase order
 * that is persisted after a user places an order.
 */
public class PurchaseOrder implements Serializable{

  protected String poId;
  protected String userId;
  protected String emailId;
  protected String locale;
  protected Calendar orderDate;
  protected ContactInfo shippingInfo;
  protected ContactInfo billingInfo;
  protected float totalPrice;
  protected CreditCard creditCard;
  protected int headCount;
  protected Calendar startDate;
  protected Calendar endDate;
  protected String departureCity;
  protected Activity[] activities;
  protected Lodging lodging;
  protected Transportation departureFlightInfo;
  protected Transportation returnFlightInfo;

  // Constructor
  public PurchaseOrder() {}

  public PurchaseOrder(String poId, String userId, String emailId,
                       String locale, Calendar orderDate,
                       ContactInfo shippingInfo, ContactInfo billingInfo,
                       float totalPrice, CreditCard creditCard, int headCount,
                       Calendar startDate, Calendar endDate, String departureCity,
                       Activity[] activities, Lodging lodging,
                       Transportation departureFlightInfo,
                       Transportation returnFlightInfo) {
    this.poId = poId;
    this.shippingInfo = shippingInfo;
    this.userId = userId;
    this.emailId = emailId;
    this.locale = locale;
    this.orderDate = orderDate;
    this.billingInfo = billingInfo;
    this.totalPrice = totalPrice;
    this.creditCard = creditCard;
    this.headCount = headCount;
    this.startDate = startDate;
    this.endDate = endDate;
    this.departureCity = departureCity;
    this.activities = activities;
    this.lodging = lodging;
    this.departureFlightInfo = departureFlightInfo;
    this.returnFlightInfo = returnFlightInfo;
  }

  // getter methods
  public String getPoId() {
    return poId;
  }

  public String getUserId() {
    return userId;
  }

  public String getEmailId() {
    return emailId;
  }

  public String getLocale() {
    return locale;
  }

  public Calendar getOrderDate() {
    return orderDate;
  }

  public ContactInfo getShippingInfo() {
    return shippingInfo;
  }

  public ContactInfo getBillingInfo() {
    return billingInfo;
  }

  public float getTotalPrice() {
    return totalPrice;
  }

  public CreditCard getCreditCard() {
    return creditCard;
  }

  public int getHeadCount() {
    return headCount;
  }

  public Calendar getStartDate() {
    return startDate;
  }

  public Calendar getEndDate() {
    return endDate;
  }

  public String getDepartureCity() {
    return departureCity;
  }

  public Activity[] getActivities() {
    return activities;
  }

  public Lodging getLodging() {
    return lodging;
  }

  public Transportation getDepartureFlightInfo() {
    return departureFlightInfo;
  }

  public Transportation getReturnFlightInfo() {
    return returnFlightInfo;
  }

  // setter methods
  public void setPoId(String poId) {
    this.poId = poId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public void setOrderDate(Calendar orderDate) {
    this.orderDate = orderDate;
  }

  public void setShippingInfo(ContactInfo shippingInfo) {
    this.shippingInfo = shippingInfo;
  }

  public void setBillingInfo(ContactInfo billingInfo) {
    this.billingInfo = billingInfo;
  }

  public void setTotalPrice(float totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void setCreditCard(CreditCard creditCard) {
    this.creditCard = creditCard;
  }

  public void setHeadCount(int headCount) {
    this.headCount = headCount;
  }

  public void setStartDate(Calendar startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(Calendar endDate) {
    this.endDate = endDate;
  }

  public void setDepartureCity(String departureCity) {
    this.departureCity = departureCity;
  }

  public void setActivities(Activity[] activity) {
    this.activities =  activity;
  }

  public void setLodging(Lodging lodging) {
    this.lodging = lodging;
  }

  public void setDepartureFlightInfo(Transportation departureFlightInfo) {
    this.departureFlightInfo = departureFlightInfo;
  }

  public void setReturnFlightInfo(Transportation returnFlightInfo) {
    this.returnFlightInfo = returnFlightInfo;
  }

}
