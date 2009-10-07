package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

import com.sun.j2ee.blueprints.opc.purchaseorder.Address;

/**
 * Local Home Interface for the AddressBean.
 **/

public interface AddressLocalHome extends EJBLocalHome {

  public AddressLocal create(Address address) throws CreateException;

  public AddressLocal findByPrimaryKey(Object key) throws FinderException;

}
