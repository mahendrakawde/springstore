/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityPurchaseOrderLocal.java,v 1.3 2004/05/26 00:06:00 inder Exp $ */
package com.sun.j2ee.blueprints.activitysupplier.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

/**
 * Local Interface for the ActivityPurchaseOrderBean.
 **/

public interface ActivityPurchaseOrderLocal  extends EJBLocalObject {

  public String getPoId();
}
