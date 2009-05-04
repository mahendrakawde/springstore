package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Implementation class for the  AddressBean .
 * AddressBean is a CMP Entity Bean
 **/

public abstract class AddressBean  implements EntityBean {

  private EntityContext entityContext = null;

  public Object ejbCreate(Address address) throws CreateException {

    setStreetName1(address.getStreetName1());
    setStreetName2(address.getStreetName2());
    setCity(address.getCity());
    setState(address.getState());
    setPostalCode(address.getPostalCode());
    setCountry(address.getCountry());

    return null;
  }

  public void ejbPostCreate(Address address) throws CreateException {}

  //getters and setters for CMP fields
  public abstract void setStreetName1(String streetName1);

  public abstract void setStreetName2(String streetName2);

  public abstract void setCity(String city);

  public abstract void setState(String state);

  public abstract void setPostalCode(String postalCode);

  public abstract void setCountry(String country);

  public abstract String getStreetName1();

  public abstract String getStreetName2();

  public abstract String getCity();

  public abstract String getState();

  public abstract String getPostalCode();

  public abstract String getCountry();

  public Address getDetails() {
    Address address = new Address();
    address.setStreetName1(getStreetName1());
    address.setStreetName2(getStreetName2());
    address.setCity(getCity());
    address.setState(getState());
    address.setPostalCode(getPostalCode());
    address.setCountry(getCountry());
    return address;

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
