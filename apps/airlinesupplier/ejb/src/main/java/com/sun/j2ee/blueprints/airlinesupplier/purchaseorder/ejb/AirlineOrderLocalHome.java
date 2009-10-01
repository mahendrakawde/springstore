/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AirlineOrderLocalHome.java,v 1.2 2004/05/26 00:06:07 inder Exp $ */

package com.sun.j2ee.blueprints.airlinesupplier.purchaseorder.ejb;

import javax.ejb.*;
import com.sun.j2ee.blueprints.airlinesupplier.powebservice.*;

/**
 * Local Home Interface for the AirlineOrderBean.
 */
public interface AirlineOrderLocalHome  extends EJBLocalHome {

  public AirlineOrderLocal create(AirlineOrder flight)
  throws CreateException;
  public AirlineOrderLocal findByPrimaryKey(String flightOrderId)
  throws FinderException;
}
