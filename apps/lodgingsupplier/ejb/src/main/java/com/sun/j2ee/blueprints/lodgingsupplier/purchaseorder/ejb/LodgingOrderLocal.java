/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingOrderLocal.java,v 1.4 2004/05/26 00:06:42 inder Exp $ */

package com.sun.j2ee.blueprints.lodgingsupplier.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

/**
 * Local Interface for the LodgingOrderBean.
 */
public interface LodgingOrderLocal extends EJBLocalObject {

    public String getOrderId();

    public String getLodgingId();

    public long getStartDate();

    public long getEndDate();

    public int getHeadCount();
}
