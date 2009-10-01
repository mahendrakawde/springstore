/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CustomerBean.java,v 1.2 2004/05/26 00:06:16 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite;

import java.io.Serializable;

// customer component imports
import com.sun.j2ee.blueprints.customer.Address;
import com.sun.j2ee.blueprints.customer.ContactInformation;
import com.sun.j2ee.blueprints.customer.Account;

/**
 * A JavaBeans component representing an account.
 */
public class CustomerBean implements Serializable {


    private String userId = null;
    private  String streetName1;
    private String streetName2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String telephone;
    private String email;
    private Address address;
    private String familyName;
    private String givenName;


    public CustomerBean(Account acct) {
      this.userId = acct.getUserId();

      this.streetName1 = acct.getContactInformation().getAddress().getStreetName1();
      this.streetName2 = acct.getContactInformation().getAddress().getStreetName2();
      this.city = acct.getContactInformation().getAddress().getCity();
      this.state = acct.getContactInformation().getAddress().getState();  
      this.zipCode = acct.getContactInformation().getAddress().getZipCode();
      this.country = acct.getContactInformation().getAddress().getCountry();

      this.givenName = acct.getContactInformation().getGivenName();
      this.familyName = acct.getContactInformation().getFamilyName();
      this.email = acct.getContactInformation().getEMail();
      this.telephone = acct.getContactInformation().getTelephone();    
    }

    public CustomerBean(String userId, String streetName1, String streetName2,
      String city, String state, String zipCode, 
      String country, String familyName, String givenName, 
      String telephone, String email) {
      this.userId = userId;
      this.streetName1 = streetName1;
      this.streetName2 = streetName2;
      this.city = city;
      this.state = state;
      this.zipCode = zipCode;
      this.country = country;
      this.givenName = givenName;
      this.familyName = familyName;
      this.email = email;
      this.telephone = telephone;    
    }

       //getter methods

   public String getUserId() {
        return userId;
    }
    
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

    public String getGivenName(){
        return givenName;
    }

    public String getFamilyName(){
        return familyName;
    }

    public String getEmail(){
        return email;
    }

    public String getTelephone(){
        return telephone;
    }


    //setter methods

   public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setStreetName1(String streetName1) {
  this.streetName1 = streetName1;
    }

    public void setStreetName2(String streetName2) {
      this.streetName2 = streetName2;
    }

    public void setCity(String city) {
  this.city = city;
    }

    public void setState(String state) {
  this.state = state;
    }

    public void setCountry(String country) {
  this.country = country;
    }

    public void setZipCode(String zipCode) {
  this.zipCode = zipCode;
    }

    public void setGivenName(String givenName){    
      this.givenName = givenName;
    }

    public void setFamilyName(String familyName){
       this.familyName = familyName;
    }

    public void setEmail(String email){
      this.email = email;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String toString() {
      String space = " ";
      String ret =  getUserId() + space  + getStreetName1() + space + getStreetName2() + space  + getCity() + space  + getState() + space + getCountry() + space  + getZipCode() + space + getGivenName() + space + getFamilyName() + space + getEmail() + space + getTelephone() + "\n";

      return ret;
    }

}
