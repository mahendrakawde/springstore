/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityPurchaseOrderLocalHome.java,v 1.2 2004/05/26 00:06:00 inder Exp $ */
package com.sun.j2ee.blueprints.activitysupplier.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.activitysupplier.powebservice.*;

/**
 * Local Home Interface for the ActivityPurchaseOrderBean.
 **/

public interface ActivityPurchaseOrderLocalHome extends EJBLocalHome {

  public ActivityPurchaseOrderLocal create(ActivityOrder po) 
      throws CreateException;
  public ActivityPurchaseOrderLocal findByPrimaryKey(String poId) 
      throws FinderException;
}
