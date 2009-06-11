/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityLocalHome.java,v 1.2 2004/05/26 00:06:56 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Local Home Interface for the ActivityBean.
 **/

public interface ActivityLocalHome  extends EJBLocalHome {

  public ActivityLocal create(Activity activity) throws CreateException;
  public ActivityLocal findByPrimaryKey(Object key) throws FinderException;

}
