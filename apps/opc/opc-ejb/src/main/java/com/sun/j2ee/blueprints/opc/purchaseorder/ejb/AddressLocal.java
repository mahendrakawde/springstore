package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.EJBLocalObject;

import com.sun.j2ee.blueprints.opc.purchaseorder.Address;

/**
 * Local Interface for the AddressBean.
 **/

public interface AddressLocal extends EJBLocalObject {

  public void setStreetName1(String streetName1);

  public String getStreetName1();

  public void setStreetName2(String streetName2);

  public String getStreetName2();

  public void setCity(String city);

  public String getCity();

  public void setState(String state);

  public String getState();

  public void setPostalCode(String postalCode);

  public String getPostalCode();

  public void setCountry(String country);

  public String getCountry();

  public Address getDetails();
}
