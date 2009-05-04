/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CreditCardLocalHome.java,v 1.2 2004/05/26 00:06:57 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Local Home Interface for the CreditCardBean.
 **/


public interface CreditCardLocalHome extends EJBLocalHome {

  public CreditCardLocal create(CreditCard card) throws CreateException;
  public CreditCardLocal findByPrimaryKey(Object key) throws FinderException;


}
