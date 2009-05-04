/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AirlineOrderLocal.java,v 1.3 2004/05/26 00:06:07 inder Exp $ */

package com.sun.j2ee.blueprints.airlinesupplier.purchaseorder.ejb;

import javax.ejb.*;
import java.util.*;

/**
 * Local Interface for the AirlineOrderBean.
 */
public interface AirlineOrderLocal extends EJBLocalObject {

    public String getOrderId();

    public String getDepFlightId();

    public long getDepFlightDate();

    public String getRetFlightId();

    public long getRetFlightDate();

    public int getHeadCount();
}
