/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: WebController.java,v 1.3 2004/07/27 21:23:50 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.util.Collection;
import javax.servlet.http.*;
import javax.servlet.*;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.*;



/**
 * This class is essentially where the business logic for the web tier
 * is processed. Implementations of this class may implement the processing
 * as a factory method to process com.sun.j2ee.blueprints.waf.controller.Command
 * objects directly or they may be processed in the EJB tier in other implmentations
 * of htis class that work as more of a proxy class.
 */
public interface WebController extends java.io.Serializable {


    /**
     * constructor for an HTTP client.
     * @param session the HttpSession object of the client
     */
    public void init(HttpSession session );

  
    /**
     * feeds the specified event to the state machine of the business logic. 
     *
     * @param ev is the current com.sun.j2ee.blueprints.waf.controller.Event
     * @param session is the current javax.servlet.http.HttpSession 
     * @return an com.sun.j2ee.blueprints.waf.event.EventResponse resulting in the
     *         processing of this event. 
     * @exception com.sun.j2ee.blueprints.waf.event.EventException <description>
     * 
     */
    public  EventResponse handleEvent(Event ev, HttpSession session) throws EventException; 

}

