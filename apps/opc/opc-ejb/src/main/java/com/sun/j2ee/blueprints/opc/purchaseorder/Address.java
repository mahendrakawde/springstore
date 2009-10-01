/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Address.java,v 1.2 2004/05/26 00:06:54 inder Exp $ */

package com.sun.j2ee.blueprints.opc.purchaseorder;

import java.io.*;

public class Address implements Serializable{

  private String streetName1;
  private String streetName2;
  private String city;
  private String state;
  private String postalCode;
  private String country;

  // Constructor

  public Address() {}

  public Address(String streetName1, String streetName2, String city,
                 String state, String postalCode, String country) {
    this.streetName1 = streetName1;
    this.streetName2 = streetName2;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.country = country;
    return;
  }

  // getter methods

  public String getStreetName1() {
    return streetName1;
  }

  public String getStreetName2() {
    return streetName2;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  // setter methods

  public void setStreetName1(String streetName) {
    this.streetName1 = streetName;
    return;
  }

  public void setStreetName2(String streetName) {
    this.streetName2 = streetName;
    return;
  }

  public void setCity(String city) {
    this.city = city;
    return;
  }

  public void setState(String state) {
    this.state = state;
    return;
  }

  public void setCountry(String country) {
    this.country = country;
    return;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
    return;
  }

}


