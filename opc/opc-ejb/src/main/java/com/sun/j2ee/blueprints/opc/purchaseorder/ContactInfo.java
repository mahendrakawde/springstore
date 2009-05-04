/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ContactInfo.java,v 1.2 2004/05/26 00:06:54 inder Exp $ */

package com.sun.j2ee.blueprints.opc.purchaseorder;

import java.io.*;

public class ContactInfo implements Serializable{

  private String familyName;
  private String givenName;
  private Address address;
  private String email;
  private String phone;

  public ContactInfo() {}

  public ContactInfo(String familyName, String givenName, Address address,
                     String email, String phone) {
    this.familyName = familyName;
    this.givenName = givenName;
    this.address = address;
    this.email = email;
    this.phone = phone;
    return;
  }

  // getter methods

  public String getFamilyName() {
    return familyName;
  }

  public String getGivenName() {
    return givenName;
  }

  public Address getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  // setter methods

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
    return;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
    return;
  }

  public void setAddress(Address address) {
    this.address = address;
    return;
  }

  public void setEmail(String email) {
    this.email = email;
    return;
  }

  public void setPhone(String phone) {
    this.phone = phone;
    return;
  }

}

