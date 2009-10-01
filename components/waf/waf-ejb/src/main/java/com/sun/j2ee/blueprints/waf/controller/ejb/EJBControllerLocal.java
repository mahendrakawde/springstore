/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: EJBControllerLocal.java,v 1.2 2004/05/26 00:07:27 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.ejb;

// J2EE imports
import javax.ejb.EJBLocalObject;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.EventResponse;
import com.sun.j2ee.blueprints.waf.controller.EventException;

/** 
 * This is the EJB-tier controller of the MVC.
 * It is implemented as a session EJB. It controls all the activities 
 * that happen in a client session. 
 * It also provides mechanisms to access other session EJBs.
 */
public interface EJBControllerLocal extends EJBLocalObject {


    /** 
     * Feeds the specified event to the state machine of the business logic. 
     * @return an EventResponse.
     */
    public EventResponse processEvent(Event ese) 
  throws  EventException;
}

