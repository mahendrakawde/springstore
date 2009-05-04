/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ContactInfoLocal.java,v 1.2 2004/05/26 00:06:56 inder Exp $ */

package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Local Interface for the ContactInfoBean.
 **/

public interface ContactInfoLocal  extends EJBLocalObject {

  public void setFamilyName(String familyName);

  public String getFamilyName();

  public void setGivenName(String givenName);

  public String getGivenName();

  public void setAddress(AddressLocal address);

  public AddressLocal getAddress();

  public void setEmail(String email);

  public String getEmail();

  public void setPhone(String phone);

  public String getPhone();

  public ContactInfo getDetails();
}
