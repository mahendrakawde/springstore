/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Address.java,v 1.2 2004/05/26 00:06:21 inder Exp $ */

package com.sun.j2ee.blueprints.customer;

/**
 * This class represents all the data needed 
 * for a customer's address.
 * This class is meant to be immutable.
 */
public class Address implements java.io.Serializable {

  private String streetName1;
  private String streetName2;
  private String city;
  private String state;
  private String zipCode;
  private String country;

  public Address() {}

  public Address(String streetName1, String streetName2, String city,
                 String state, String zipCode, String country) {
    this.streetName1 = streetName1;
    this.streetName2 = streetName2;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.country = country;
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

  public String getZipCode() {
    return zipCode;
  }

  public String toString() {
    return "Address[streeName1=" + streetName1 + ", "
      + "streetName2=" + streetName2 + ", "
      + "city=" + city + ", "
      + "state=" + state + ", "
      + "zipCode=" + zipCode + ", "
      + "country=" + country + "]";
  }
}


