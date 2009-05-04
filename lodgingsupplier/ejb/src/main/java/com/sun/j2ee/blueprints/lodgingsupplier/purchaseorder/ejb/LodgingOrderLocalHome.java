/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingOrderLocalHome.java,v 1.3 2004/05/26 00:06:43 inder Exp $ */

package com.sun.j2ee.blueprints.lodgingsupplier.purchaseorder.ejb;

import javax.ejb.*;
import com.sun.j2ee.blueprints.lodgingsupplier.powebservice.*;

/**
 * Local Home Interface for the LodgingOrderBean.
 */
public interface LodgingOrderLocalHome  extends EJBLocalHome {

  public LodgingOrderLocal create(LodgingOrder lodge) throws CreateException;
  public LodgingOrderLocal findByPrimaryKey(String lodgeOrderId)
  throws FinderException;
}
