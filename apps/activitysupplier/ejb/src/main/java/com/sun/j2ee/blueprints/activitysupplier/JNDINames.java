/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: JNDINames.java,v 1.7 2005/03/08 00:19:47 smitha Exp $ */
package com.sun.j2ee.blueprints.activitysupplier;

/**
 * JNDI names of various EJBs
 */
public class JNDINames {

    // JNDI name of contact info  EJB
    public static final String ACTIVITY_PURCHASEORDER_EJB =
  "java:comp/env/ejb/local/activitysupplier/ActivityPurchaseOrder";
    public static final String ACTIVITY_DETAILS_EJB =
  "java:comp/env/ejb/local/activitysupplier/ActivityDetails";

    public static final String ACT_QUEUECONNECTIONFACTORY =
        "java:comp/env/jms/activity/QueueConnectionFactory";

    public static final String ACT_QUEUE =
        "java:comp/env/jms/activity/ActivityQueue";

    public static final String BROKER_SERVICE_NAME = 
  "java:comp/env/service/WebServiceBroker";
}

