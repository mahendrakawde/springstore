/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: PurchaseOrderLocalHome.java,v 1.2 2004/05/26 00:06:58 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Local Home Interface for the PurchaseOrderBean.
 **/

public interface PurchaseOrderLocalHome  extends EJBLocalHome {

  public PurchaseOrderLocal create(PurchaseOrder po) throws CreateException;
  public PurchaseOrderLocal findByPrimaryKey(String poId) throws FinderException;


}
