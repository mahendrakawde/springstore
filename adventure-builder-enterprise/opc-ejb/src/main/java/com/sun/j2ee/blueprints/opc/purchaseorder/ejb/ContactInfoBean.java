/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ContactInfoBean.java,v 1.2 2004/05/26 00:06:56 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;


/**
 * Implementation class for the  ContactInfoBean .
 * ContactInfoBean is a CMP Entity Bean .
 **/

public abstract class ContactInfoBean  implements EntityBean {

  private EntityContext entityContext = null;

  public Object ejbCreate(ContactInfo contactInfo) throws CreateException {

    setGivenName(contactInfo.getGivenName());
    setFamilyName(contactInfo.getFamilyName());
    setPhone(contactInfo.getPhone());
    setEmail(contactInfo.getEmail());

    return null;
  }

  public void ejbPostCreate(ContactInfo contactInfo) throws CreateException {

    try {
      ServiceLocator sl = new ServiceLocator();
      AddressLocalHome alh = (AddressLocalHome) sl.getLocalHome(JNDINames.
          ADDR_EJB);
      AddressLocal address = alh.create(contactInfo.getAddress());
      setAddress(address);
    }
    catch (ServiceLocatorException se) {
      throw new CreateException("Exception saving PO:" +
                                se.getMessage());
    }
  }

  public ContactInfo getDetails() {
    ContactInfo contactInfo = new ContactInfo();
    contactInfo.setGivenName(getGivenName());
    contactInfo.setFamilyName(getFamilyName());
    contactInfo.setPhone(getPhone());
    contactInfo.setEmail(getEmail());
    contactInfo.setAddress(getAddress().getDetails());
    return contactInfo;

  }

  //getters and setters for CMP fields
  public abstract void setFamilyName(String familyName);

  public abstract void setGivenName(String givenName); 

  public abstract void setEmail(String email);

  public abstract void setPhone(String phone);

  public abstract String getFamilyName();

  public abstract String getGivenName();

  public abstract String getEmail();

  public abstract String getPhone();

  //getters and setters for CMR fields
  public abstract void setAddress(AddressLocal address);

  public abstract AddressLocal getAddress();


  public void ejbLoad() {
  }

  public void ejbRemove() throws RemoveException {
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
