/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: JNDINames.java,v 1.5 2005/03/08 00:19:31 smitha Exp $ */

package com.sun.j2ee.blueprints.airlinesupplier;


/**
 * JNDI names of various EJBs
 */
public class JNDINames {

    // JNDI name of contact info  EJB
    public static final String AIRLINE_ORDER_EJB =
  "java:comp/env/ejb/local/airlinesupplier/AirlineOrder";

    public static final String AIRLINE_QUEUECONNECTIONFACTORY =
        "java:comp/env/jms/airline/QueueConnectionFactory";

    public static final String AIRLINE_QUEUE =
        "java:comp/env/jms/airline/AirlineQueue";

    public static final String BROKER_SERVICE_NAME = 
  "java:comp/env/service/WebServiceBroker";
}

