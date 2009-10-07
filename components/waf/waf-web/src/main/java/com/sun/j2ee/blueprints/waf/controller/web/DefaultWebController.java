/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: DefaultWebController.java,v 1.3 2004/07/27 21:23:50 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import javax.servlet.http.HttpSession;

import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.EventException;
import com.sun.j2ee.blueprints.waf.controller.EventResponse;

/**
 *
 */
public class DefaultWebController implements WebController {

    public DefaultWebController() {
    }

    /**
     * constructor for an HTTP client.
     * @param session the HttpSession  object of the clients
     */
    public void init(HttpSession session) {
    }
    
    /**
     * feeds the specified event to the state machine of the business logic. 
     *
     * @param ev is the current event 
     * @return com.sun.j2ee.blueprints.waf.controller.EventResponse object which can be extend to carry any payload. 
     * @exception com.sun.j2ee.blueprints.waf.controller.EventException <description>
     * @exception com.sun.j2ee.blueprints.wafcontroller.GeneralFailureException
     */
    public synchronized EventResponse handleEvent(Event ev, HttpSession session) 
  throws EventException {
            return null;
    }

     /** 
     * frees up all the resources associated with this controller and
     * destroys itself.
     */
    public synchronized void destroy(HttpSession session) {
    }
}

