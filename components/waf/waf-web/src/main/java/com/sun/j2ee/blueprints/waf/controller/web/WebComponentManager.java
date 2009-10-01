/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: WebComponentManager.java,v 1.2 2004/05/26 00:07:32 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.beans.Beans;

// J2EE Imports
import javax.servlet.*;
import javax.servlet.http.*;
import javax.ejb.*;
import javax.naming.*;

// Service Locator Imports
import com.sun.j2ee.blueprints.servicelocator.web.ServiceLocator;

// WAF Imports
import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;
import com.sun.j2ee.blueprints.waf.util.JNDINames;
import com.sun.j2ee.blueprints.waf.controller.*;

// Tracer Imports
import com.sun.j2ee.blueprints.util.tracer.Debug;


/** 
 * This implmentation class of the ComponentManager provides
 * access to services in the web tier and ejb tier. 
 *  
 */
public class WebComponentManager implements ComponentManager, java.io.Serializable {
    
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


