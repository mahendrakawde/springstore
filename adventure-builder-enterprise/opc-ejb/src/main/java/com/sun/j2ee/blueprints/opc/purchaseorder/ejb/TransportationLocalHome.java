/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: TransportationLocalHome.java,v 1.2 2004/05/26 00:06:58 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Local Home Interface for the TransportationBean.
 **/


public interface TransportationLocalHome  extends EJBLocalHome {

  public TransportationLocal create(Transportation transportation) throws
      CreateException;

  public TransportationLocal findByPrimaryKey(Object key) throws
      FinderException;
}
