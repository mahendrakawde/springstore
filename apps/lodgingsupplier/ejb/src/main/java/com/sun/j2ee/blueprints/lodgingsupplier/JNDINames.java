/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: JNDINames.java,v 1.5 2005/03/08 00:19:15 smitha Exp $ */

package com.sun.j2ee.blueprints.lodgingsupplier;


/**
 * JNDI names of various EJBs
 */
public class JNDINames {

    // JNDI name of contact info  EJB
    public static final String LODGE_ORDER_EJB =
  "java:comp/env/ejb/local/lodgingsupplier/LodgingOrder";

    public static final String LODGE_QUEUECONNECTIONFACTORY =
        "java:comp/env/jms/lodging/QueueConnectionFactory";

    public static final String LODGE_QUEUE =
        "java:comp/env/jms/lodging/LodgingQueue";

    public static final String BROKER_SERVICE_NAME = 
  "java:comp/env/service/WebServiceBroker";
}

