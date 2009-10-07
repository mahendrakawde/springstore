/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: WebComponentManager.java,v 1.2 2004/05/26 00:07:32 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.servicelocator.web.ServiceLocator;
import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;


/** 
 * This implmentation class of the ComponentManager provides
 * access to services in the web tier and ejb tier. 
 *  
 */
public class WebComponentManager implements ComponentManager, java.io.Serializable {
    
	protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected ServiceLocator sl = null;
    
    public WebComponentManager() {
    }

    /**
     * Web Only Applications will do there model updates in the 
     * individual actions themselves thuse we do not need 
     * another set of classes for command processing.
     */
    public WebController getWebController(HttpSession session) {
         return null;
    }

    /**
     * Initialize
     */
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();  
        session.setAttribute(WebKeys.COMPONENT_MANAGER, this);
    }

    /**
     * Destroy whatever needs destroying
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
    }
}


